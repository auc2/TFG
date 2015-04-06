package cat.udl.eps.softarch.hello.service;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.Swimmer;
import org.springframework.data.domain.Sort;



public interface SwimmerService {

    public Swimmer getSwimmer(Long userId);

    public Swimmer addSwimmer(Swimmer sw);

    public List<Swimmer> findAll();

 	public Sort sortByIdAsc();

// PENSAR...
 //   Greeting addReportToSwimmer(Report report);

  //  Greeting updateGreetingFromUser(Greeting updateGreeting, Long greetingId);

  //  void removeGreetingFromUser(Long greetingId);
}
