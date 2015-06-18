package cat.udl.eps.softarch.hello.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import cat.udl.eps.softarch.hello.model.Teacher;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.repository.TeacherRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepositoryCustom;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TeacherServiceImpl implements TeacherService {
    final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    TeacherRepository     teacherRepository;

    @Autowired
    SwimmerGroupRepository     swimmerGroupRepository;

    @Autowired
    SwimmerGroupRepositoryCustom    swimmerGroupRepositoryCustom;



    @Transactional(readOnly = true)
    @Override
    public Teacher getTeacher(Long userId) {
     
        Teacher t = teacherRepository.findOne(userId);
        logger.info("Teacher name {}", t.getTeacherName());
        return t;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAll(){

      // List<Teacher> teachers = new ArrayList<Teacher>();
       return teacherRepository.findAll(sortByIdAsc());
    }

    public Sort sortByIdAsc(){
        return new Sort(Sort.Direction.ASC, "id");
    }


  
    @Transactional(readOnly = false)
    @Override
    public Teacher addTeacher(Teacher t) {

        teacherRepository.save(t);
        return t;
    }


    @Transactional(readOnly = false)
    @Override
    public Teacher addTeacher(Teacher teacher, ArrayList<Long> groupsListId){

        List<Long> groupsid = groupsListId;
        for( Long groupid : groupsid ){

            SwimmerGroup group = swimmerGroupRepository.findOne(groupid); 
            group.setTeacher(teacher); //Assign teacher to group
            swimmerGroupRepository.save(group); //Update group

            teacher.addSwimmerGroup(group); //Assign groups in a list to new teacher.
        }

        teacherRepository.save(teacher);   
        return teacher;
    }

    @Transactional
    @Override
    public void removeTeacher(Long teacherId){

        Teacher teacher = teacherRepository.findOne(teacherId);
        List<SwimmerGroup> groups = swimmerGroupRepository.findSwimmerGroupByTeacher(teacher);

        //All this groups, after now they will have no teacher.
        for( SwimmerGroup group : groups ){

            teacher.removeSwimmerGroup(group); 
            group.setTeacher(null); 
        }

        teacherRepository.delete(teacher);              
    }


    @Transactional
    @Override
    public Teacher updateTeacher(Teacher updateTeacher, Long oldTeacherId, ArrayList<Long> newsGroupsListId) throws IOException {

        Teacher oldTeacher = teacherRepository.findOne(oldTeacherId);

        oldTeacher.setTeacherName(updateTeacher.getTeacherName());
        oldTeacher.setSurname(updateTeacher.getSurname());
        oldTeacher.setCity(updateTeacher.getCity());
        oldTeacher.setTelephone(updateTeacher.getTelephone());
        oldTeacher.setEmail(updateTeacher.getEmail());

        if(updateTeacher.getPhoto().length > 0) oldTeacher.setPhotoBytes(updateTeacher.getPhoto());


        List<SwimmerGroup> allGroupsTeacher = swimmerGroupRepository.findSwimmerGroupByTeacher(oldTeacher);   
        List<SwimmerGroup> newsGroupsTeacher = swimmerGroupRepositoryCustom.getSwimmerGroupsByListID(newsGroupsListId);


        /*Check if there are some group changed to not assigned*/
        for( SwimmerGroup groupid : allGroupsTeacher ){

            if(!newsGroupsTeacher.contains(groupid)){ //Group not assigned to this teacher from now
                 oldTeacher.removeSwimmerGroup(groupid); 
                 groupid.setTeacher(null); 
            }
        }


        /*Check if there ara a new group to add or old to delete*/
        for( Long groupid : newsGroupsListId ){

            SwimmerGroup group = swimmerGroupRepository.findOne(groupid); 

            if(!allGroupsTeacher.contains(group)){
                group.setTeacher(oldTeacher); //Assign teacher to group
                swimmerGroupRepository.save(group); //Update group

                oldTeacher.addSwimmerGroup(group); //Assign groups in a list to teacher.
           }
        }

        return teacherRepository.save(oldTeacher);
    }


}
