package cat.udl.eps.softarch.hello.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.model.Teacher;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.repository.TeacherRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import org.springframework.data.domain.Sort;





@Service
public class SwimmerGroupServiceImpl implements SwimmerGroupService {
    final Logger logger = LoggerFactory.getLogger(SwimmerGroupServiceImpl.class);

    @Autowired
    SwimmerGroupRepository     swimmerGroupRepository;

    @Transactional(readOnly = true)
    @Override
    public SwimmerGroup getSwimmerGroup(Long userId) {
        SwimmerGroup group = swimmerGroupRepository.findOne(userId);
        logger.info("SwimmerGroup id {}", group.getId());
        return group;
    }



    @Autowired 
    TeacherRepository       teacherRepository;

    @Autowired 
    SwimmerRepository       swimmerRepository;

   
    @Override
    public void addSwimmerGroup(SwimmerGroup group, Long teacherId) {

        Teacher teacher = teacherRepository.findOne(teacherId); 
        group.setTeacher(teacher);
        //teacher.addSwimmerGroup(group);
        //teacherRepository.save(teacher);


        swimmerGroupRepository.save(group);
    }


    @Transactional(readOnly = false)
    @Override
    public void addSwimmerGroup(SwimmerGroup group, Long teacherId,  ArrayList<Long> swimmersListId) {

        Teacher teacher = teacherRepository.findOne(teacherId); 
        group.setTeacher(teacher);

       
        System.out.println("Afegint alumnes checked al nou grup.");

        List<Long> swimmersId = swimmersListId;
        for( Long swimmerId : swimmersId ){

            System.out.println("Nou nedador.....");

            Swimmer swimmer = swimmerRepository.findOne(swimmerId); 
            System.out.println("-NOM................."+swimmer.getSwimmerName());

            group.addSwimmer(swimmer);

            swimmer.setGroup(group);
            swimmerRepository.save(swimmer);
        }

        swimmerGroupRepository.save(group);   


        teacher.addSwimmerGroup(group);
        teacherRepository.save(teacher);

        for( Long swimmerId : swimmersId ){

           Swimmer swimmer = swimmerRepository.findOne(swimmerId); 

           swimmer.setGroup(group);
           swimmerRepository.save(swimmer);
        }    
    }



    @Transactional(readOnly = true)
    @Override   
    public List<SwimmerGroup> findAll(){

       List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>();
       return swimmerGroupRepository.findAll(sortByIdAsc());
    }
    

    public Sort sortByIdAsc(){
        return new Sort(Sort.Direction.ASC, "id");
    }


}


/*
   @Transactional(readOnly = true)
    @Override
    public SwimmerGroup addSwimmerGroup(SwimmerGroup group){
        swimmerGroupRepository.save(group);
        return group;
    }
  */

