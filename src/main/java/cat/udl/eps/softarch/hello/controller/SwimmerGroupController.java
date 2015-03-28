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
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.service.SwimmerGroupService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;



@Controller
@RequestMapping(value = "/swimmerGroups")
public class SwimmerGroupController {
    final Logger logger = LoggerFactory.getLogger(SwimmerGroupController.class);

    @Autowired SwimmerGroupRepository       swimmerGroupRepository;
    @Autowired SwimmerGroupService       swimmerGroupService;    

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<SwimmerGroup> list(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return swimmerGroupRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
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
        return new ModelAndView("swimmerGroup", "swimmerGroup", retrieve(id));
    }



    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SwimmerGroup create(@Valid @RequestBody SwimmerGroup swimmerGroup, HttpServletResponse response) {
        logger.info("Creating swimmerGroup with level'{}' at '{}'", swimmerGroup.getLevel(),swimmerGroup.getSessionHour());
       SwimmerGroup newSwimmerGroup = swimmerGroupService.addSwimmerGroup(swimmerGroup);
       response.setHeader("Location", "/swimmerGroups/" + newSwimmerGroup.getId());
       return newSwimmerGroup;


    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@Valid @ModelAttribute("swimmerGroup") SwimmerGroup swimmerGroup, BindingResult binding, HttpServletResponse response) {
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "swimmerGroupForm";
        }
        return "redirect:/swimmerGroups/"+create(swimmerGroup, response).getId();
    }
    // Create form
    @RequestMapping(value = "/swimmerGroupForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        logger.info("Generating swimmerGroupForm for swimmerGroup creation");
        SwimmerGroup emptySwimmerGroup = new SwimmerGroup();
        return new ModelAndView("swimmerGroupForm", "swimmerGroup", emptySwimmerGroup);
    }


}
