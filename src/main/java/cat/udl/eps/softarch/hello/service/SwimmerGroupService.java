package cat.udl.eps.softarch.hello.service;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import org.springframework.data.domain.Sort;



public interface SwimmerGroupService {

    public SwimmerGroup getSwimmerGroup(Long userId);

    public void addSwimmerGroup(SwimmerGroup group);

    public void addSwimmerGroup(SwimmerGroup group, Long teacherId);

    public void addSwimmerGroup(SwimmerGroup group, ArrayList<Long> swimmersListId);

    public void addSwimmerGroup(SwimmerGroup group, Long teacherId,  ArrayList<Long> swimmersListId);

	public List<SwimmerGroup> findAll();

	public Sort sortByIdAsc();

	public void removeSwimmerGroup(Long swimmerGroupId);


}
