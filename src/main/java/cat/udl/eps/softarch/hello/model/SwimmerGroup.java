package cat.udl.eps.softarch.hello.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class SwimmerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //A triar entre un desplegable de tres opcions.    	
    @NotBlank(message = "La hora de la classe no pot estar en blanc.")
    private String sessionHour;	

    @NotBlank(message = "El nivell de la classe no pot estar en blanc.")
    private String level; 

    @ManyToOne
    private Teacher teacher;


    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false) //Si poso False, no eliminara els alunes al borrar el grup.
    private List<Swimmer> swimmers = new ArrayList<Swimmer>();



    public SwimmerGroup() {}

    //Constructor basic
    public SwimmerGroup(String sessionHour, String level) {     
      this.sessionHour = sessionHour;
      this.level = level;
    }


    //Constructor per si en la creació es posen swimmers
    public SwimmerGroup(String sessionHour, String level, List<Swimmer> swimmers) {     
      this.sessionHour = sessionHour;
      this.level = level;
      this.swimmers = swimmers;
    }

    //Constructor per si en la creació es posen swimmers i teacher
    public SwimmerGroup(String sessionHour, String level, List<Swimmer> swimmers, Teacher teacher) {     
      this.sessionHour = sessionHour;
      this.level = level;
      this.teacher = teacher;
      this.swimmers = swimmers;
    }

    public long getId() { return id; }

    public String getSessionHour() { return sessionHour; }
    public void setSessionHour(String sessionHour) { this.sessionHour = sessionHour; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    

   public List<Swimmer> getSwimmers() {
        return swimmers;
   }

    public void addSwimmer(Swimmer newSwimmer) {
        swimmers.add(newSwimmer);
    }

    public void removeSwimmer(Swimmer swimmer) {
        swimmers.remove(swimmer);
    }

    public Teacher getTeacher() { return teacher; }

    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}
