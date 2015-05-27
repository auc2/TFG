package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerRepository;
import cat.udl.eps.softarch.hello.service.SwimmerGroupService;
import cat.udl.eps.softarch.hello.service.SwimmerService;
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
    public String createHTML(@Valid @ModelAttribute("swimmer") Swimmer swimmer, BindingResult binding, @RequestParam Long groupId, HttpServletResponse response) {
       
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "swimmerForm";
        }

        Swimmer newSwimmer = new Swimmer();

        //SwimmerGroup group = swimmerGroupRepository.findOne(groupId);

        if (groupId == 9999){
            logger.info("Afegint nedador sense grup assignat.");
           newSwimmer = swimmerService.addSwimmer(swimmer);
        } 
        else{
             logger.info("Afegint nedador amb grup assignat.");
            newSwimmer = swimmerService.addSwimmer(swimmer, groupId);
        }
        
        return "redirect:/swimmers/"+newSwimmer.getId();
    }


    // Create form
    @RequestMapping(value = "/swimmerForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
      
      //  SwimmerGroupController swGrCont = new SwimmerGroupController();
        List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>();

        groups =  swimmerGroupService.findAll();

        logger.info("Generating swimmerForm for swimmer creation");
        Swimmer emptySwimmer = new Swimmer();

        ModelAndView model = new ModelAndView("swimmerForm");
        model.addObject("swimmer", emptySwimmer);
        model.addObject("groups",groups); // ("nom per referir-nos al jsp", objecte)

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

      //  if (groupId == 9999){
        //   updateSwimmer = swimmerService.addSwimmer(swimmer);
       // } 
      //  else{
      //      updateSwimmer = swimmerService.addSwimmer(swimmer, groupId);
      //  }

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

}
