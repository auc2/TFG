package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.model.Teacher;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.service.SwimmerGroupService;
import cat.udl.eps.softarch.hello.service.SwimmerService;
import cat.udl.eps.softarch.hello.service.TeacherService;
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



@Controller
@RequestMapping(value = "/swimmerGroups")
public class SwimmerGroupController {
    final Logger logger = LoggerFactory.getLogger(SwimmerGroupController.class);

    @Autowired SwimmerGroupRepository       swimmerGroupRepository;
    @Autowired SwimmerGroupService       swimmerGroupService;    

    @Autowired TeacherService       teacherService;
    @Autowired SwimmerService       swimmerService;    

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<SwimmerGroup> list(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int size) {
        PageRequest request = new PageRequest(page, size);
        return swimmerGroupRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int size) {
        return new ModelAndView("swimmerGroups", "swimmerGroups", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SwimmerGroup retrieve(@PathVariable("id") Long id) {
        logger.info("Retrieving swimmerGroup number {}", id);
        Preconditions.checkNotNull(swimmerGroupRepository.findOne(id), "SwimmerGroup with id %s not found", id);
        return swimmerGroupService.getSwimmerGroup(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {

        SwimmerGroup swimmerGroup = retrieve(id);

        List<Swimmer> swimmers = swimmerGroup.getSwimmers();    

        ModelAndView model = new ModelAndView("swimmerGroup");
        model.addObject("swimmerGroup", swimmerGroup);
        model.addObject("swimmers",swimmers);

        return model;
    }



    // CREATE
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@Valid @ModelAttribute("swimmerGroup") SwimmerGroup swimmerGroup, BindingResult binding, @RequestParam Long teacherId,
         @RequestParam(required = false, defaultValue = "") ArrayList<Long> swimmersListId, HttpServletResponse response) {
        
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "swimmerGroupForm";
        }

        if  (teacherId == 9999 && swimmersListId.size() == 0) swimmerGroupService.addSwimmerGroup(swimmerGroup);
        if  (teacherId == 9999 && swimmersListId.size() > 0)  swimmerGroupService.addSwimmerGroup(swimmerGroup, swimmersListId);
        if  (teacherId != 9999 && swimmersListId.size() == 0) swimmerGroupService.addSwimmerGroup(swimmerGroup, (Long)teacherId);
        if  (teacherId != 9999 && swimmersListId.size() > 0) swimmerGroupService.addSwimmerGroup(swimmerGroup, (Long)teacherId, swimmersListId);
        
        return "redirect:/swimmerGroups/"+swimmerGroup.getId();
    }



    // Create form
    @RequestMapping(value = "/swimmerGroupForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {

        TeacherController teaCont = new TeacherController();
        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers =  teacherService.findAll();

        List<Swimmer> swimmers = swimmerService.findAll();

        List<String> sessionHours = new ArrayList<String>();
        //FER-HO EN UN ENUM??
        sessionHours.add("    -   ");
        sessionHours.add("1ra Hora  10:15 - 11:15");
        sessionHours.add("2na Hora  11:15 - 12:15");
        sessionHours.add("3ra Hora  12:15 - 13:15");

     //    Levels levels;
       
      //  for(LevelsGroups level: LevelsGroups.values())
        //    System.out.println("valor--> "+level.name());
       // String[] levels = Level.values(); //Get values from the enum Levels


        logger.info("Generating swimmerGroupForm for swimmerGroup creation");
        SwimmerGroup emptySwimmerGroup = new SwimmerGroup();


        ModelAndView model = new ModelAndView("swimmerGroupForm");
        model.addObject("swimmerGroup", emptySwimmerGroup);
        model.addObject("teachers",teachers); // ("nom per referir-nos al jsp", objecte)
        model.addObject("swimmers",swimmers);
        model.addObject("sessionHours",sessionHours);
      //  model.addObject("levels", levels);

        return model;
    }


    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {

        logger.info("Deleting SwimmerGroup number {}", id);
        Preconditions.checkNotNull(swimmerGroupRepository.findOne(id), "SwimmerGroup with id %s not found", id);
        swimmerGroupService.removeSwimmerGroup(id);

        return "redirect:/swimmerGroups";
    }



}
