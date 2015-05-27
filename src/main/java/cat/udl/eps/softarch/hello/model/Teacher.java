package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import java.sql.Blob;
import java.io.InputStream;


@Entity
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private Blob photo;

    @NotBlank(message = "El nom del professor no pot estar en blanc.")
    private String teachername;

    @NotBlank(message = "El cognom del professor no pot estar en blanc.")
    private String surname;

    @NotBlank(message = "E-mail no pot estar en blanc")
    @Email(message = "E-mail ha de ser valit")
    private String email;

    @NotBlank(message = "El telefon del professor no pot estar en blanc.")
    private String telephone;

    @NotBlank(message = "La ciutat del professor no pot estar en blanc.")
    private String city;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
    private List<SwimmerGroup> swimmerGroups = new ArrayList<SwimmerGroup>();

    public Teacher() { }


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

    public void removeAllSwimmerGroup() {
        swimmerGroups.clear();
    }


/*
    public InputStream get_user_photo() throws Exception {     
       
        
        if(photo!=null)
            return photo.getBinaryStream();
        else
            return null;
    }*/

}
