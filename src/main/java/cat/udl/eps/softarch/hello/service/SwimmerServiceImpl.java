package cat.udl.eps.softarch.hello.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import org.springframework.data.domain.Sort;


@Service
public class SwimmerServiceImpl implements SwimmerService {
    final Logger logger = LoggerFactory.getLogger(SwimmerServiceImpl.class);

    @Autowired
    SwimmerRepository     swimmerRepository;


    @Transactional(readOnly = true)
    @Override
    public Swimmer getSwimmer(Long userId) {
        Swimmer sw = swimmerRepository.findOne(userId);
        logger.info("Swimmer name {}", sw.getSwimmerName());
        return sw;
    }


      
    @Transactional(readOnly = true)
    @Override
    public List<Swimmer> findAll(){

       List<Swimmer> swimmers = new ArrayList<Swimmer>();
       return swimmerRepository.findAll(sortByIdAsc());
    }

    public Sort sortByIdAsc(){
        return new Sort(Sort.Direction.ASC, "id");
    }



   @Transactional(readOnly = true)
    @Override
    public Swimmer addSwimmer(Swimmer sw){
        swimmerRepository.save(sw);
        return sw;
    }



    @Autowired
    SwimmerGroupRepository    swimmergroupRepository;



    @Override
    public void addSwimmer(Swimmer sw, Long groupId){

    
            SwimmerGroup group = swimmergroupRepository.findOne(groupId); 
            sw.setGroup(group);
            swimmerRepository.save(sw);
    

        //group.addSwimmer(sw);
        //swimmerGroupRepository.save(group);
    }


  
}
