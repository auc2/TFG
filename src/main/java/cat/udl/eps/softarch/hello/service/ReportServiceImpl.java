package cat.udl.eps.softarch.hello.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.AnualReport;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import cat.udl.eps.softarch.hello.repository.ReportRepository;
import org.springframework.data.domain.Sort;





@Service
public class ReportServiceImpl implements ReportService {


    final Logger logger = LoggerFactory.getLogger(SwimmerGroupServiceImpl.class);

    @Autowired
    SwimmerRepository    swimmerRepository;


    @Autowired
    ReportRepository    reportRepository;




    @Transactional(readOnly = true)
    @Override
    public AnualReport getReport(Long reportId) {
        AnualReport report = reportRepository.findOne(reportId);
        logger.info("Report num {}", report.getId());
        return report;
    }


    @Transactional(readOnly = false)
    @Override
    public AnualReport addReportSwimmer(AnualReport report, Long swimmerid){

    
      Swimmer swimmer = swimmerRepository.findOne(swimmerid);
      String level = swimmer.getGroup().getLevel();

      //System.out.println("****************+LEVEL ---------->"+report.getLevel());  //Però em fa posar el setLevel.. sino no funciona

     if(level.equals("Dofins")){ report.setLevel("Dofins");   }
     if(level.equals("Sardines")){ report.setLevel("Sardines"); }

       reportRepository.save(report);      

       swimmer.addReport(report);

       swimmerRepository.save(swimmer);

       return report;
    }


    @Transactional
    @Override
    public void removeReportFromSwimmer(Long reportid, Long swimmerid){
        

        AnualReport report =  reportRepository.findOne(reportid);
        Swimmer swimmer = swimmerRepository.findOne(swimmerid);

        swimmer.removeReport(report);

        reportRepository.delete(report);
    }


    @Transactional
    @Override
    public AnualReport updateReportFromSwimmer(AnualReport updatedReport, Long oldreportid){

        AnualReport oldreport = reportRepository.findOne(oldreportid);

        oldreport.setValue1(updatedReport.getValue1());
        oldreport.setValue2(updatedReport.getValue2());
        oldreport.setValue3(updatedReport.getValue3());
        oldreport.setValue4(updatedReport.getValue4());
        oldreport.setValue5(updatedReport.getValue5());
        oldreport.setValue6(updatedReport.getValue6());
        oldreport.setValue7(updatedReport.getValue7());
        oldreport.setValue8(updatedReport.getValue8());
        oldreport.setValue9(updatedReport.getValue9());
        oldreport.setValue10(updatedReport.getValue10());

        oldreport.setComment(updatedReport.getComment());

        return reportRepository.save(oldreport);
    }


}



