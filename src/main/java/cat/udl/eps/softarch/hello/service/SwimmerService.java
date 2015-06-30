package cat.udl.eps.softarch.hello.service;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.Swimmer;
import org.springframework.data.domain.Sort;
import java.io.IOException;



public interface SwimmerService {

    public Swimmer getSwimmer(Long userId);

    public List<Swimmer> findAll();

 	public Sort sortByIdAsc();

    public Swimmer addSwimmer(Swimmer sw);
    public Swimmer addSwimmer(Swimmer sw, Long groupId);   

    public void removeSwimmer(Long swimmerId);

    public Swimmer updateSwimmer(Swimmer updateSwimmer, Long oldSwimmerId, Long groupId) throws IOException;

  //  public void removeReportFromSwimmer(Long reportId);




// PENSAR...
 //   Greeting addReportToSwimmer(Report report);

  //  Greeting updateGreetingFromUser(Greeting updateGreeting, Long greetingId);
}
