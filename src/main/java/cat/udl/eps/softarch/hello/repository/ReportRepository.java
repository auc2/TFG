package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.AnualReport;
import java.util.ArrayList;
import java.util.List;




public interface ReportRepository extends JpaRepository<AnualReport, Long> {

    // List<Report> findReportBySwimmer(@Param("swimmer") Swimmer swimmer);

}