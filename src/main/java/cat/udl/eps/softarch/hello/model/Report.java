package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity
public interface Report{


    public long getId();

    public List<String> getQuestions();

    public List<String> getValues();
    public void setValues(List<String> values);




    public String getValue();
    public void setValue(String value);

}