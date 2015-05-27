package cat.udl.eps.softarch.hello.service;

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
import org.springframework.data.domain.Sort;

@Service
public class TeacherServiceImpl implements TeacherService {
    final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    TeacherRepository     teacherRepository;

    @Autowired
    SwimmerGroupRepository     swimmerGroupRepository;


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
    public Teacher updateTeacher(Teacher updateTeacher, Long oldTeacherId, ArrayList<Long> newsGroupsListId){

        Teacher oldTeacher = teacherRepository.findOne(oldTeacherId);

        oldTeacher.setTeacherName(updateTeacher.getTeacherName());
        oldTeacher.setSurname(updateTeacher.getSurname());
        oldTeacher.setCity(updateTeacher.getCity());
        oldTeacher.setTelephone(updateTeacher.getTelephone());
        oldTeacher.setEmail(updateTeacher.getEmail());


       // List<SwimmerGroup> groupsTeacher = swimmerGroupRepository.findSwimmerGroupByTeacher(oldTeacher);

        oldTeacher.removeAllSwimmerGroup(); //Delete all the groups relations to the the teacher, to add the new ones.

        List<Long> newGroupsId = newsGroupsListId;
        for( Long groupid : newGroupsId ){


System.out.println("Group--->"+groupid);
//PENDING -->>> DELETE UNSELECTED GROUPS PREVIOUS TO THE TEACHER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11






            SwimmerGroup group = swimmerGroupRepository.findOne(groupid); 

            //if(!groupsTeacher.contains(group)){
                group.setTeacher(oldTeacher); //Assign teacher to group
                swimmerGroupRepository.save(group); //Update group

                oldTeacher.addSwimmerGroup(group); //Assign groups in a list to teacher.
           // }
        }



        return teacherRepository.save(oldTeacher);
    }

}
