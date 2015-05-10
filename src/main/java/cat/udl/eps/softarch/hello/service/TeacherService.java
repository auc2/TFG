package cat.udl.eps.softarch.hello.service;
import cat.udl.eps.softarch.hello.model.Teacher;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;


public interface TeacherService {

    public Teacher getTeacher(Long userId);

    public Teacher addTeacher(Teacher t);

    public Teacher addTeacher(Teacher teacher, ArrayList<Long> groupsListId);   

	public List<Teacher> findAll();

 	public Sort sortByIdAsc();


 //   Greeting addGreetingToUser(Greeting greeting);

  //  Greeting updateGreetingFromUser(Greeting updateGreeting, Long greetingId);

  //  void removeGreetingFromUser(Long greetingId);
}
