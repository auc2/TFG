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
import cat.udl.eps.softarch.hello.repository.TeacherRepository;
import cat.udl.eps.softarch.hello.service.TeacherService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;



@Controller
@RequestMapping(value = "/teachers")
public class TeacherController {
    final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired TeacherRepository       teacherRepository;
    @Autowired TeacherService       teacherService;    

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Teacher> list(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return teacherRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHTML(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("teachers", "teachers", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Teacher retrieve(@PathVariable("id") Long id) {
        logger.info("Retrieving teacher number {}", id);
        Preconditions.checkNotNull(teacherRepository.findOne(id), "Teacher with id %s not found", id);
        return teacherService.getTeacher(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("teacher", "teacher", retrieve(id));
    }



    



    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Teacher create(@Valid @RequestBody Teacher teacher, HttpServletResponse response) {
        logger.info("Creating teacher with name'{}'", teacher.getTeacherName());
      //  Teacher newTeacher = SwimmerService.addTeacherToSwimmer(teacher);
       Teacher newTeacher = teacherService.addTeacher(teacher);
       response.setHeader("Location", "/teachers/" + newTeacher.getId());
       return newTeacher;


    }
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult binding, HttpServletResponse response) {
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "teacherForm";
        }
        return "redirect:/teachers/"+create(teacher, response).getId();
    }
    // Create form
    @RequestMapping(value = "/teacherForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        logger.info("Generating teacherForm for teacher creation");
        Teacher emptyTeacher = new Teacher();
       // emptyTeacher.setDate(new Date());
        return new ModelAndView("teacherForm", "teacher", emptyTeacher);
    }






}
