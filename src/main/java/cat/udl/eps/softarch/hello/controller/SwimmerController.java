package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import cat.udl.eps.softarch.hello.service.SwimmerGroupService;
import cat.udl.eps.softarch.hello.service.SwimmerService;
import cat.udl.eps.softarch.hello.service.ReportService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import cat.udl.eps.softarch.hello.model.*;




@Controller
@RequestMapping(value = "/swimmers")
public class SwimmerController {
    final Logger logger = LoggerFactory.getLogger(SwimmerController.class);

    @Autowired SwimmerRepository       swimmerRepository;
    @Autowired SwimmerGroupRepository       swimmerGroupRepository;

    @Autowired SwimmerService       swimmerService;   
    @Autowired SwimmerGroupService       swimmerGroupService;   

   // @Autowired ReportRepository    reportRepository; 
    @Autowired ReportService    reportService; 

 

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Swimmer> list(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int size) {
        PageRequest request = new PageRequest(page, size);
        return swimmerRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int size) {
        return new ModelAndView("swimmers", "swimmers", list(page, size));
    }

    // RETRIEVE SWIMMER
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Swimmer retrieve(@PathVariable("id") Long id) {
        logger.info("Retrieving swimmer number {}", id);
        Preconditions.checkNotNull(swimmerRepository.findOne(id), "Swimmer with id %s not found", id);
        return swimmerService.getSwimmer(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("swimmer", "swimmer", retrieve(id));
    }


    // RETRIEVE REPORT
   // @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  //  @ResponseBody
  //  public AnualReport retrieveReport(@PathVariable("id") Long id) {
  //      logger.info("Retrieving report number {}", id);
  //      Preconditions.checkNotNull(reportRepository.findOne(id), "Report with id %s not found", id);
   //     return reportService.getSwimmer(id);
   // }

   // @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
   // public ModelAndView retrieveHTMLReport(@PathVariable( "id" ) Long id) {
  //      return new ModelAndView("report", "report", retrieve(id));
  //  }








    // CREATE
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public ModelAndView createHTML(@Valid @ModelAttribute("swimmer") Swimmer swimmer, BindingResult binding, @RequestParam Long groupId, HttpServletResponse response) {
       
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);

            List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>();

            groups =  swimmerGroupService.findAll();

            ModelAndView model = new ModelAndView("swimmerForm");
            model.addObject("groups",groups); // ("nom per referir-nos al jsp", objecte)

            return model;
        }

        Swimmer newSwimmer = new Swimmer();

        if (groupId == 9999){
            logger.info("Afegint nedador sense grup assignat.");
           newSwimmer = swimmerService.addSwimmer(swimmer);
        } 
        else{
             logger.info("Afegint nedador amb grup assignat.");
            newSwimmer = swimmerService.addSwimmer(swimmer, groupId);
        }
        
        return new ModelAndView("redirect:/swimmers/"+newSwimmer.getId());
    }


    // Create form
    @RequestMapping(value = "/swimmerForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
      
        List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>();

        groups =  swimmerGroupService.findAll();

        logger.info("Generating swimmerForm for swimmer creation");
        Swimmer emptySwimmer = new Swimmer();

        List<String> puntuation = new ArrayList<String>();
            puntuation.add("Necessita Millorar");
          puntuation.add("Regular");
            puntuation.add("Be");
            puntuation.add("Molt be");
           puntuation.add("Perfecte");


        ModelAndView model = new ModelAndView("swimmerForm");
        model.addObject("swimmer", emptySwimmer);
        model.addObject("groups",groups); // ("nom per referir-nos al jsp", objecte)
        model.addObject("puntuation", puntuation);

        return model;
    }




    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {

        logger.info("Deleting Swimmer number {}", id);
        Preconditions.checkNotNull(swimmerRepository.findOne(id), "Swimmer with id %s not found", id);
        swimmerService.removeSwimmer(id);

        return "redirect:/swimmers";
    }



    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long oldSwimmerId, @Valid @ModelAttribute("swimmer") Swimmer updateSwimmer,
                         BindingResult binding, @RequestParam Long groupId) {
     
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "swimmerForm";
        }


        logger.info("Updating swimmer {}, new content is '{}'", oldSwimmerId, updateSwimmer.getSwimmerName());
        Preconditions.checkNotNull(swimmerRepository.findOne(oldSwimmerId), "Swimmer with id %s not found", oldSwimmerId);
        Swimmer swimmerUpdated = swimmerService.updateSwimmer(updateSwimmer, oldSwimmerId, groupId);

        return "redirect:/swimmers/"+swimmerUpdated.getId();
    }

    // Generate Update form
    @RequestMapping(value = "/{id}/swimmerForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long oldSwimmerId) {
     
        logger.info("Generating form for updating swimmer number {}", oldSwimmerId);
        Preconditions.checkNotNull(swimmerRepository.findOne(oldSwimmerId), "Swimmer with id %s not found", oldSwimmerId);

        List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>();
        groups =  swimmerGroupService.findAll();


        ModelAndView model = new ModelAndView("swimmerForm");
        model.addObject("swimmer", swimmerRepository.findOne(oldSwimmerId));
        model.addObject("groups",groups); // ("nom per referir-nos al jsp", objecte)

        return model;

    }






 
 // CREATE REPORT FORM
   @RequestMapping(value = "/{id}/reportForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView reportForm(@PathVariable("id") Long id) {
     


     //PREPARAR UN REPORT PER ASSIGNAR A L'USUARI AMB EL ID per paràmetre.


        logger.info("Generating form for create report for swimmer number {}", id);
        Preconditions.checkNotNull(swimmerRepository.findOne(id), "Swimmer with id %s not found", id);

       // Report report = null;

        AnualReport report = null;
        Swimmer sw = swimmerRepository.findOne(id);

        String level = sw.getGroup().getLevel();


        //if(level.equals("Dofins")){
       //     logger.info("Generating form with level Dofins");
       //     report = new DofiReport();
       // }
        //if level .. 
        //if level .. 
        //if level .. 

         if(level.equals("Dofins")){
            logger.info("Generating form with level Dofins");
            report = new AnualReport();
            report.setLevel("Dofins");
         }

         if(level.equals("Sardines")){
            logger.info("Generating form with level Sardines");
            report = new AnualReport();
            report.setLevel("Sardines");
         }

            List<String> questions = report.getQuestions();

            List<String> puntuation = new ArrayList<String>();
            puntuation.add("Necessita Millorar");
            puntuation.add("Regular");
            puntuation.add("Be");
            puntuation.add("Molt be");
            puntuation.add("Perfecte");

        

        ModelAndView model = new ModelAndView("reportForm");
        model.addObject("report", report);
        model.addObject("puntuation", puntuation);
        model.addObject("questions", questions);

        return model;

    }




  // CREATE REPORT
  @RequestMapping(value = "/reports/{id}", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public ModelAndView createHTML(@PathVariable("id") Long swimmerId, @Valid @ModelAttribute("swimmer") AnualReport report, BindingResult binding, HttpServletResponse response) {

        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);

            ModelAndView model = new ModelAndView("reportForm");
            return model;
        }

        Swimmer swimmer = swimmerRepository.findOne(swimmerId);

        AnualReport newReport = reportService.addReportSwimmer(report, swimmer);

        return new ModelAndView("redirect:/swimmers/reports/"+newReport.getId()); //FALTE FER AQUEST METODE RETRIEVE!!!
    }



}
