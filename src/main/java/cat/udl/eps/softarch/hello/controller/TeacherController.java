package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.model.Teacher;
import cat.udl.eps.softarch.hello.repository.TeacherRepository;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;
import cat.udl.eps.softarch.hello.service.SwimmerGroupService;
import cat.udl.eps.softarch.hello.service.TeacherService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Blob;


@Controller
@RequestMapping(value = "/teachers")
public class TeacherController {
    final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired TeacherRepository       teacherRepository;
    @Autowired TeacherService       teacherService;  

    @Autowired SwimmerGroupService       swimmerGroupService;  
    @Autowired SwimmerGroupRepository  swimmerGroupRepository; 


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


        Teacher teacher = retrieve(id);
    
        /*Carrego les entitats groups en el temps d'execució, ja que groups està amb FetchType.Lazy*/
        List<SwimmerGroup> groups = swimmerGroupRepository.findSwimmerGroupByTeacher(teacher);      


         List<SwimmerGroup> allgroups = swimmerGroupService.findAll();
         List<SwimmerGroup> groupsNoAssignats = new ArrayList<SwimmerGroup>(); //Groups with no teacher assigned.

           for( SwimmerGroup group : allgroups ){

                if(group.getTeacher() == null) groupsNoAssignats.add(group);
            }  


        ModelAndView model = new ModelAndView("teacher");
        model.addObject("teacher", teacher);
        model.addObject("groups",groups);
        model.addObject("groupsNoAssignats",groupsNoAssignats); //Mostrar llista de grups per assignar. I donar la possibilitat de fer Update, per afegir grups.

        return model;
    }


    @RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable("id") Long id) throws IOException, SQLException{

        Teacher teacher = teacherService.getTeacher(id);

        return teacher.getPhoto();
    }




    // CREATE
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data", produces="text/html")
    public ModelAndView createHTML(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult binding,
                                   @RequestParam(required = false, defaultValue = "") ArrayList<Long> groupsListId,
                                   HttpServletResponse response) throws IOException {
      
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);

            List<SwimmerGroup> allgroups =  swimmerGroupService.findAll();
            List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>(); //Groups with no teacher assigned.

            for( SwimmerGroup group : allgroups ){
                if(group.getTeacher() == null) groups.add(group);
            }

            ModelAndView model = new ModelAndView("teacherForm");
            model.addObject("groups",groups); 
            return model;
        }

        if(groupsListId.size() > 0)  teacher = teacherService.addTeacher(teacher, groupsListId);
        else teacher = teacherService.addTeacher(teacher);
     
        return new ModelAndView("redirect:/teachers/"+teacher.getId());
    }


    // Create form
    @RequestMapping(value = "/teacherForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {

        logger.info("Generating teacherForm for teacher creation");
        Teacher emptyTeacher = new Teacher();

        List<SwimmerGroup> allgroups = new ArrayList<SwimmerGroup>();
        allgroups =  swimmerGroupService.findAll();

        List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>(); //Groups with no teacher assigned.

           for( SwimmerGroup group : allgroups ){

                if(group.getTeacher() == null) groups.add(group);
            }


        ModelAndView model = new ModelAndView("teacherForm");
        model.addObject("teacher", emptyTeacher);
        model.addObject("groups",groups); 


        return model;
    }


    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {

        logger.info("Deleting Teacher number {}", id);
        Preconditions.checkNotNull(teacherRepository.findOne(id), "Teacher with id %s not found", id);
        teacherService.removeTeacher(id);

        return "redirect:/teachers";
    }


    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long oldTeacherId, @Valid @ModelAttribute("swimmer") Teacher updateTeacher,
                         BindingResult binding, @RequestParam(required = false, defaultValue = "") ArrayList<Long> groupsListId) throws IOException{
     
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "teacherForm";
        }

        logger.info("Updating teacher {}, new content is '{}'", oldTeacherId, updateTeacher.getTeacherName());
        Preconditions.checkNotNull(teacherRepository.findOne(oldTeacherId), "Teacher with id %s not found", oldTeacherId);
        Teacher updatedTeacher = teacherService.updateTeacher(updateTeacher, oldTeacherId, groupsListId);

        return "redirect:/teachers/"+updatedTeacher.getId();
    }

    // Generate Update form
    @RequestMapping(value = "/{id}/teacherForm", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long oldTeacherId) {
     
        logger.info("Generating form for updating teacher number {}", oldTeacherId);
        Preconditions.checkNotNull(teacherRepository.findOne(oldTeacherId), "teacher with id %s not found", oldTeacherId);

        Teacher oldTeacher = teacherRepository.findOne(oldTeacherId);

        List<SwimmerGroup> allgroups = new ArrayList<SwimmerGroup>();
        allgroups =  swimmerGroupService.findAll();

        List<SwimmerGroup> groups = new ArrayList<SwimmerGroup>(); //Groups with no teacher assigned.

           for( SwimmerGroup group : allgroups ){

                if(group.getTeacher() == null) groups.add(group);

            }

        List<SwimmerGroup> groupsTeacher = new ArrayList<SwimmerGroup>();
        groupsTeacher = swimmerGroupRepository.findSwimmerGroupByTeacher(oldTeacher);


        ModelAndView model = new ModelAndView("teacherForm");
        model.addObject("teacher", oldTeacher);
        model.addObject("groups", groups); 
        model.addObject("groupsTeacher", groupsTeacher);
        return model;
    }




}
