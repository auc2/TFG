package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.model.Teacher;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;






@Repository
public class SwimmerGroupRepositoryCustomImpl implements SwimmerGroupRepositoryCustom {

    @Autowired 
    SwimmerGroupRepository  swimmerGroupRepository; 

    @Transactional
    @Override
    public List<SwimmerGroup> getSwimmerGroupsByListID( ArrayList<Long> groupsListId){

  		List<SwimmerGroup> swGroups = new ArrayList<SwimmerGroup>();
      SwimmerGroup group = new SwimmerGroup();

         for( Long groupid : groupsListId ){

            group = swimmerGroupRepository.findOne(groupid); 
            swGroups.add(group);
         }

      return swGroups;
    }

}

