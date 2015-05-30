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
import cat.udl.eps.softarch.hello.repository.SwimmerRepositoryCustom;
import org.springframework.data.domain.Sort;





@Service
public class SwimmerGroupServiceImpl implements SwimmerGroupService {
    final Logger logger = LoggerFactory.getLogger(SwimmerGroupServiceImpl.class);

    @Autowired
    SwimmerGroupRepository     swimmerGroupRepository;

    @Autowired
    SwimmerRepositoryCustom    swimmerRepositoryCustom;

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



    @Transactional(readOnly = true)
    @Override
    public SwimmerGroup addSwimmerGroup(SwimmerGroup group){
       
        swimmerGroupRepository.save(group);
        return group;

    }


    @Transactional(readOnly = false)
    @Override
    public SwimmerGroup addSwimmerGroup(SwimmerGroup group, Long teacherId) {

        Teacher teacher = teacherRepository.findOne(teacherId); 
        group.setTeacher(teacher);
        swimmerGroupRepository.save(group);

        teacher.addSwimmerGroup(group);
        teacherRepository.save(teacher);
        return group;
    }


    @Transactional(readOnly = false)
    @Override
    public SwimmerGroup addSwimmerGroup(SwimmerGroup group, ArrayList<Long> swimmersListId){

        List<Long> swimmersId = swimmersListId;
        for( Long swimmerId : swimmersId ){

            Swimmer swimmer = swimmerRepository.findOne(swimmerId); 
            group.addSwimmer(swimmer);
            swimmerGroupRepository.save(group);   

            swimmer.setGroup(group);
            swimmerRepository.save(swimmer);
        }

        return group;
    }



    @Transactional(readOnly = false)
    @Override
    public SwimmerGroup addSwimmerGroup(SwimmerGroup group, Long teacherId,  ArrayList<Long> swimmersListId) {


        Teacher teacher = teacherRepository.findOne(teacherId); 
        group.setTeacher(teacher);

        List<Long> swimmersId = swimmersListId;
        for( Long swimmerId : swimmersId ){

            Swimmer swimmer = swimmerRepository.findOne(swimmerId); 
            group.addSwimmer(swimmer);
            swimmerGroupRepository.save(group);   

            swimmer.setGroup(group);
            swimmerRepository.save(swimmer);
        }


        teacher.addSwimmerGroup(group);
        teacherRepository.save(teacher);
  
        return group;
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



    @Transactional
    @Override   
    public void removeSwimmerGroup(Long swimmerGroupId){

        SwimmerGroup group = swimmerGroupRepository.findOne(swimmerGroupId);

        Teacher teacher = group.getTeacher();

        if(teacher != null){
           group.setTeacher(null);
           teacher.removeSwimmerGroup(group); 
        }

        List<Swimmer> swimmers =  swimmerRepository.findSwimmerByGroup(group);

        for( Swimmer swimmer : swimmers ){
            group.removeSwimmer(swimmer);
            swimmer.setGroup(null);
        }

        swimmerGroupRepository.delete(group);  
    }


    @Transactional
    @Override   
    public SwimmerGroup updateSwimmerGroup(SwimmerGroup updateSwimmerGroup, Long oldSwimmerGroupId, ArrayList<Long> newsSwimmersListId){

        SwimmerGroup oldGroup = swimmerGroupRepository.findOne(oldSwimmerGroupId);

        oldGroup.setSessionHour(updateSwimmerGroup.getSessionHour());
        oldGroup.setLevel(updateSwimmerGroup.getLevel());
        oldGroup.setTeacher(updateSwimmerGroup.getTeacher());
      

        List<Swimmer> allSwimmerInGroup = swimmerRepository.findSwimmerByGroup(oldGroup);
        List<Swimmer> newsSwimmersInGroup = swimmerRepositoryCustom.getSwimmersByListID(newsSwimmersListId);


        /*Check if there are some swimmer changed to not assigned*/
        for( Swimmer swimmerid : allSwimmerInGroup ){

            if(!newsSwimmersInGroup.contains(swimmerid)){ //Swimmer not assigned in this group until now
                 oldGroup.removeSwimmer(swimmerid); 
                 swimmerid.setGroup(null); 
            }
        }


        /*Check if there ara a new swimmer to add or old to delete*/
        for( Long swimmerid : newsSwimmersListId ){

            Swimmer swimmer = swimmerRepository.findOne(swimmerid); 

            if(!allSwimmerInGroup.contains(swimmer)){
                swimmer.setGroup(oldGroup); //Assign swimmer to group
                swimmerRepository.save(swimmer); //Update swimmer

                oldGroup.addSwimmer(swimmer); //Assign swimmers in a list to group.
           }
        }



        return swimmerGroupRepository.save(oldGroup);

    }



}



