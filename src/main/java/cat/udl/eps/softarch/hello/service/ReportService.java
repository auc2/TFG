package cat.udl.eps.softarch.hello.service;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.Report;
import org.springframework.data.domain.Sort;



public interface ReportService{

   
	public Report addReportSwimmer(Report report, Swimmer swimmer);

  //  public void removeReportFromSwimmer(Long reportId);




// PENSAR...
 //   Greeting addReportToSwimmer(Report report);

  //  Greeting updateGreetingFromUser(Greeting updateGreeting, Long greetingId);
}
