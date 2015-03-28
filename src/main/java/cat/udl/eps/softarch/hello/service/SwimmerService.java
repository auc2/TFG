package cat.udl.eps.softarch.hello.service;
import cat.udl.eps.softarch.hello.model.Swimmer;


public interface SwimmerService {

    Swimmer getSwimmer(Long userId);


    Swimmer addSwimmer(Swimmer sw);

// PENSAR...
 //   Greeting addReportToSwimmer(Report report);

  //  Greeting updateGreetingFromUser(Greeting updateGreeting, Long greetingId);

  //  void removeGreetingFromUser(Long greetingId);
}
