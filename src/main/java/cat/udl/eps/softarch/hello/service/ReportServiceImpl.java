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
import cat.udl.eps.softarch.hello.model.Report;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
//import cat.udl.eps.softarch.hello.repository.ReportRepository;
import org.springframework.data.domain.Sort;





@Service
public class ReportServiceImpl implements ReportService {


    final Logger logger = LoggerFactory.getLogger(SwimmerGroupServiceImpl.class);

    @Autowired
    SwimmerRepository    swimmerRepository;

   // @Autowired
   // ReportRepository    reportRepository;





    @Transactional(readOnly = false)
    @Override
    public Report addReportSwimmer(Report report, Swimmer swimmer){


       // reportRepository.save(report);

       // swimmer.addReport(report);
       // swimmerRepository.save(swimmer);


        return report;
    }


}



