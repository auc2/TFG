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
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;


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



    @Transactional(readOnly = false)
    @Override
    public Swimmer addSwimmer(Swimmer sw){

        swimmerRepository.save(sw);
        return sw;
    }



    @Autowired
    SwimmerGroupRepository    swimmerGroupRepository;




    @Transactional(readOnly = false)
    @Override
    public Swimmer addSwimmer(Swimmer sw, Long groupId){

    
            SwimmerGroup group = swimmerGroupRepository.findOne(groupId); 
            sw.setGroup(group);
            swimmerRepository.save(sw); 

            group.addSwimmer(sw);
            swimmerGroupRepository.save(group); 

            return sw; 
    }



    @Transactional
    @Override
    public void removeSwimmer(Long swimmerId){
   
        Swimmer sw = swimmerRepository.findOne(swimmerId);
        SwimmerGroup group = sw.getGroup();

        if(group != null){
             group.removeSwimmer(sw);
             sw.setGroup(null);
        }
        swimmerRepository.delete(sw);     
    }


    @Transactional
    @Override
    public Swimmer updateSwimmer(Swimmer updateSwimmer, Long oldSwimmerId, Long newGroupId) throws IOException {

        Swimmer oldSwimmer = swimmerRepository.findOne(oldSwimmerId);

        oldSwimmer.setSwimmerName(updateSwimmer.getSwimmerName());
        oldSwimmer.setSurname(updateSwimmer.getSurname());
        oldSwimmer.setCity(updateSwimmer.getCity());
        oldSwimmer.setTelephone(updateSwimmer.getTelephone());
        oldSwimmer.setEmail(updateSwimmer.getEmail());

        if(updateSwimmer.getPhoto().length > 0) oldSwimmer.setPhotoBytes(updateSwimmer.getPhoto());


        SwimmerGroup oldGroup = new SwimmerGroup();
        if(newGroupId != 9999){ //Afegint grup
            
             oldGroup = oldSwimmer.getGroup();

                if(oldSwimmer.getGroup() != null){
                             oldSwimmer.setGroup(null);
                             oldGroup.removeSwimmer(oldSwimmer);
                             swimmerGroupRepository.save(oldGroup); 
                             swimmerRepository.save(oldSwimmer);
                }

             oldGroup = swimmerGroupRepository.findOne(newGroupId);
             oldSwimmer.setGroup(oldGroup);
             oldGroup.addSwimmer(oldSwimmer);


        }else{ //Treient grup
                 oldGroup = oldSwimmer.getGroup();
                 if (oldGroup == null) return swimmerRepository.save(oldSwimmer);
                 oldSwimmer.setGroup(null);
                 oldGroup.removeSwimmer(oldSwimmer);
        }

        swimmerGroupRepository.save(oldGroup); 

        return swimmerRepository.save(oldSwimmer);
    }

  
}
