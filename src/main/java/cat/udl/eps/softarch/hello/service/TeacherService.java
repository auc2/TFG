package cat.udl.eps.softarch.hello.service;

//import cat.udl.eps.softarch.hello.model.Greeting;
import cat.udl.eps.softarch.hello.model.Teacher;


public interface TeacherService {

    Teacher getTeacher(Long userId);

    Teacher addTeacher(Teacher t);

 //   Greeting addGreetingToUser(Greeting greeting);

  //  Greeting updateGreetingFromUser(Greeting updateGreeting, Long greetingId);

  //  void removeGreetingFromUser(Long greetingId);
}
