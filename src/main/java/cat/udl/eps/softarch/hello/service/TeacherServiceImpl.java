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
import cat.udl.eps.softarch.hello.repository.TeacherRepository;
import org.springframework.data.domain.Sort;

@Service
public class TeacherServiceImpl implements TeacherService {
    final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    TeacherRepository     teacherRepository;

    @Transactional(readOnly = true)
    @Override
    public Teacher getTeacher(Long userId) {
     
        Teacher t = teacherRepository.findOne(userId);
        logger.info("Teacher name {}", t.getTeacherName());
        return t;
    }


  
    @Transactional(readOnly = true)
    @Override
    public Teacher addTeacher(Teacher t) {
 
        teacherRepository.save(t);
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



   


  
}
