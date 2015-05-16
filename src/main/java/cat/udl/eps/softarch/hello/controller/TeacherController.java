package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.model.Teacher;
import cat.udl.eps.softarch.hello.repository.TeacherRepository;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping(value = "/teachers")
public class TeacherController {
    final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired TeacherRepository       teacherRepository;
    @Autowired TeacherService       teacherService;  

    @Autowired SwimmerGroupService       swimmerGroupService;    


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

        List<SwimmerGroup> groups = teacher.getSwimmerGroups();      

        ModelAndView model = new ModelAndView("teacher");
        model.addObject("teacher", teacher);
        model.addObject("groups",groups);

        return model;






       // return new ModelAndView("teacher", "teacher", retrieve(id));
    }



    @RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable("id") Long id) throws IOException, SQLException{

        Teacher teacher = teacherService.getTeacher(id);

        Blob blob =  teacher.getPhoto();

        int blobLength = (int) blob.length();
        byte[] imageBytes = blob.getBytes(1, blobLength);

        return imageBytes;
     

    }



/*

  @RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException, SQLException{

        Teacher teacher = teacherService.getTeacher(id);

        response.setContentType("image/jpeg");
        Blob blob =  teacher.getPhoto();

        int blobLength = (int) blob.length();
        byte[] imageBytes = blob.getBytes(1, blobLength);

        response.getOutputStream().write(imageBytes);
        response.getOutputStream().flush();

    }
    @ResponseBody
    @RequestMapping(value = "admin/user/{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto(@PathVariable("id") int id_sys_user) throws Exception {        
        byte[] thumb = null;

        Teacher teacher = teacherService.getTeacher(id);

        InputStream in = teacher.getPhoto();   
        if(in!=null){
            thumb = IOUtils.toByteArray(in); 
        }
        return thumb;       
}

*/


    // CREATE
    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces="text/html")
    public String createHTML(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult binding, @RequestParam(required = false, defaultValue = "") ArrayList<Long> groupsListId, HttpServletResponse response) {
      
        if (binding.hasErrors()) {
            logger.info("Validation error: {}", binding);
            return "teacherForm";
        }

        Teacher newTeacher = new Teacher();
        if(groupsListId.size() > 0)  newTeacher = teacherService.addTeacher(teacher, groupsListId);
        else newTeacher = teacherService.addTeacher(teacher);
     

        //Teacher newTeacher = teacherService.addTeacher(teacher);

        return "redirect:/teachers/"+newTeacher.getId();
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




}
