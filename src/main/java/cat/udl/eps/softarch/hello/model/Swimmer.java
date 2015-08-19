package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.sql.Blob;
import java.io.InputStream;
import java.io.IOException;




@Entity
public class Swimmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String value;

    @NotBlank(message = "El nom del nedador no pot estar en blanc.")
    private String swimmername;


    @NotBlank(message = "El cognom del nedador no pot estar en blanc.")
    private String surname;


    @NotBlank(message = "La ciutat del professor no pot estar en blanc.")
    private String city;


    @NotBlank(message = "El telefon del professor no pot estar en blanc.")
    private String telephone;


    @Email(message = "E-mail should be valid")
    private String email;

    @Lob
    private byte[] photo;


    @ManyToOne
    private SwimmerGroup group;


    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = false)
    private List<AnualReport> reports = new ArrayList<AnualReport>();


  
    public Swimmer() { }


    public long getId() { return id; }


    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getSwimmerName() { return swimmername; }
    public void setSwimmerName(String swimmername) { this.swimmername = swimmername; }

    public void setSurname(String surname) { this.surname = surname; }
    public String getSurname() { return surname; }


    public void setCity(String city) { this.city = city; }
    public String getCity() { return city; }

    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getTelephone() { return telephone; }


    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    
    public void setPhoto(MultipartFile photo) throws IOException { this.photo = photo.getBytes(); }

    public void setPhotoBytes(byte[] photo) throws IOException { this.photo = photo; }

    public byte[] getPhoto() { return photo; }



    public SwimmerGroup getGroup() { return group;  }

    public void setGroup(SwimmerGroup newSwimmerGroup) {   this.group = newSwimmerGroup;  }


    public List<AnualReport> getReports() {
            return reports;
    }


    public void addReport(AnualReport report) {
        reports.add(report);
    }

    public void removeReport(AnualReport report) {
        reports.remove(report);
    }

}
