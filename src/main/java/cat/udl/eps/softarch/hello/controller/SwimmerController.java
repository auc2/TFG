package cat.udl.eps.softarch.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Preconditions;
import cat.udl.eps.softarch.hello.model.*;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.service.SwimmerService;
import cat.udl.eps.softarch.hello.service.SwimmerGroupService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping(value = "/swimmers")
public class SwimmerController {
    final Logger logger = LoggerFactory.getLogger(SwimmerController.class);

    @Autowired SwimmerRepository       swimmerRepository;
    @Autowired SwimmerGroupRepository       swimmerGroupRepository;

    @Autowired SwimmerService       swimmerService;   
    @Autowired SwimmerGroupService       swimmerGroupService;    
 

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

    // RETRIEVE
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




    // CREATE
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@Valid @ModelAttribute("swimmer") Swimmer swimmer, @RequestParam Long groupId, BindingResult binding, HttpServletResponse response) {
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "swimmerForm";
        }

        SwimmerGroup group = swimmerGroupRepository.findOne(groupId);

        if (groupId == 9999){
           System.out.println("Afegint nedador sense grup assignat.");
           swimmerService.addSwimmer(swimmer);
        } 
        else{
            System.out.println("Afegint nedador amb grup assignat.");
            swimmerService.addSwimmer(swimmer, groupId);
        }
        
        return "redirect:/swimmers/"+swimmer.getId();
    }





    // Create form
    @RequestMapping(value = "/swimmerForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
      
        SwimmerGroupController swGrCont = new SwimmerGroupController();
        List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>();
       

        groups =  swimmerGroupService.findAll();

        logger.info("Generating swimmerForm for swimmer creation");
        Swimmer emptySwimmer = new Swimmer();


        ModelAndView model = new ModelAndView("swimmerForm");
        model.addObject("swimmer", emptySwimmer);
        model.addObject("groups",groups); // ("nom per referir-nos al jsp", objecte)


        return model;
    }


}
