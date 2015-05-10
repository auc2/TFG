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

       List<Teacher> teachers = new ArrayList<Teacher>();
       return teacherRepository.findAll(sortByIdAsc());
    }

    public Sort sortByIdAsc(){
        return new Sort(Sort.Direction.ASC, "id");
    }


  
    @Transactional(readOnly = true)
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

            teacher.addSwimmerGroup(group); //Assign groups in a list to new teacher.

            group.setTeacher(teacher); //Assign teacher to group
            swimmerGroupRepository.save(group); //Update group

        }

        teacherRepository.save(teacher);   
        return teacher;

    }


      
   


  
}
