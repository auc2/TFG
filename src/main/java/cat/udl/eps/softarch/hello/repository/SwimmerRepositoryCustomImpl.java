package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.Swimmer;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;






@Repository
public class SwimmerRepositoryCustomImpl implements SwimmerRepositoryCustom {

    @Autowired 
    SwimmerRepository  swimmerRepository; 

    @Transactional
    @Override
    public List<Swimmer> getSwimmersByListID( ArrayList<Long> swimmersListId){

  		List<Swimmer> swimmers = new ArrayList<Swimmer>();
      Swimmer swimmer = new Swimmer();

         for( Long swimmerid : swimmersListId ){

            swimmer = swimmerRepository.findOne(swimmerid); 
            swimmers.add(swimmer);
         }

      return swimmers;
    }

}

