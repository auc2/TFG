package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;



public class DofiReport implements Report{

    public String value;




    public  List<String> questions = new ArrayList<String>();
    public  List<String> values = new ArrayList<String>();


    public DofiReport() {

        this.questions.add("1.Te un nivell d'apnea capaç per realitzar més de 25 metres");
        this.questions.add("2.És capaç de realitzar el viratge de crol");
        this.questions.add("3.És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");
    }


    @Override
    public List<String> getQuestions() {

        return this.questions;
    }


    @Override
    public List<String> getValues() {

        return this.values;
    }

    @Override
    public void setValues(List<String> values) {

        this.values = values;
    }




    @Override
    public String getValue() { return value; }
    
    @Override
    public void setValue(String value) { this.value = value; }



}