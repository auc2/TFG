package cat.udl.eps.softarch.hello.service;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import org.springframework.data.domain.Sort;



public interface SwimmerGroupService {

    SwimmerGroup getSwimmerGroup(Long userId);

    void addSwimmerGroup(SwimmerGroup group, Long teacherId);

	public List<SwimmerGroup> findAll();

	public Sort sortByIdAsc();


   // SwimmerGroup addSwimmerGroup(SwimmerGroup group);

  //  SwimmerGroup deleteSwimmerGroup(SwimmerGroup group);

}
