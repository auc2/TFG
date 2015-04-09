package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import java.sql.Blob;


@Entity
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private Blob photo;

    @NotBlank(message = "Teacher name cannot be blank")
    private String teachername;

    @NotBlank(message = "El cognom del professor no pot estar en blanc.")
    private String surname;

    @NotBlank(message = "E-mail cannot be blank")
    @Email(message = "E-mail should be valid")
    private String email;

//Canviar a string amb validadors posteriors
    @NotBlank(message = "El telefon del professor no pot estar en blanc.")
    private String telephone;

    @NotBlank(message = "La ciutat del professor no pot estar en blanc.")
    private String city;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SwimmerGroup> swimmerGroups = new ArrayList<SwimmerGroup>();

    public Teacher() { }

   //Constructor basic
    public Teacher(String teachername, String surname, String city, String telephone, String email ) {
        this.teachername = teachername;
        this.surname = surname;
        this.city = city;
        this.telephone = telephone;
        this.email = email;
    }

    //Constructor amb swimmerGroup/s
    public Teacher(String teachername, String surname, String email, String telephone, String city, List<SwimmerGroup> swimmerGroups) { 
        this.teachername = teachername;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.city = city;
        this.swimmerGroups = swimmerGroups;
    }



        //Els gets seran pels update

    public long getId() { return id; }

    public void setPhoto(Blob photo) { this.photo = photo; }
    public Blob getPhoto() { return photo; }

    public void setTeacherName(String teachername) { this.teachername = teachername; }
    public String getTeacherName() { return teachername; }

    public void setSurname(String surname) { this.surname = surname; }
    public String getSurname() { return surname; }


    public void setCity(String city) { this.city = city; }
    public String getCity() { return city; }

    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getTelephone() { return telephone; }


    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }


    public List<SwimmerGroup> getSwimmerGroups() {
        return swimmerGroups;
    }

    public void addSwimmerGroup(SwimmerGroup newSwimmerGroup) {
        swimmerGroups.add(newSwimmerGroup);
    }

    public void removeSwimmerGroup(SwimmerGroup swimmerGroup) {
        swimmerGroups.remove(swimmerGroup);
    }
}
