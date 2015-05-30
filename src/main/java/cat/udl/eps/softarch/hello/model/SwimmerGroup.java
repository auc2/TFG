package cat.udl.eps.softarch.hello.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import cat.udl.eps.softarch.hello.repository.TeacherRepository;


@Entity
public class SwimmerGroup {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String level; 

    @NotBlank(message = "La hora de la classe no pot estar en blanc.")
    private String sessionHour;	


    @ManyToOne
    private Teacher teacher;


    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false) //Si poso False, no eliminara els alumnes al borrar el grup.
    private List<Swimmer> swimmers = new ArrayList<Swimmer>();


    public SwimmerGroup() {}

 

    public long getId() { return id; }

    public String getSessionHour() { return sessionHour; }
    public void setSessionHour(String sessionHour) { this.sessionHour = sessionHour; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher;  }

    
    public List<Swimmer> getSwimmers() {
            return swimmers;
    }


    public void addSwimmer(Swimmer newSwimmer) {
        swimmers.add(newSwimmer);
    }


    public void removeSwimmer(Swimmer swimmer) {
        swimmers.remove(swimmer);
    }


     /*CREAR ENTITAT TEACHERIDFORM*/

    /*   @Autowired
     TeacherRepository      teacherRepository;

    public void setTeacher(long teacher) { 

  
     Teacher t = teacherRepository.findOne(teacher);

     this.teacher = t; 
    
    }
    */
}
