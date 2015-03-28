package cat.udl.eps.softarch.hello.model;

import java.util.Date;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class AnualReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    public AnualReport() {}



    public long getId() { return id; }

    
}
