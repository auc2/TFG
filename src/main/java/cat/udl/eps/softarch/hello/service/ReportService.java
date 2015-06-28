package cat.udl.eps.softarch.hello.service;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.AnualReport;
import org.springframework.data.domain.Sort;



public interface ReportService{

   
	public AnualReport addReportSwimmer(AnualReport report,  Long swimmerid);

	public AnualReport getReport(Long reportId);

    public void removeReportFromSwimmer(Long reportid, Long swimmerid);

    public AnualReport updateReportFromSwimmer(AnualReport updatedReport, Long oldreportid);



}
