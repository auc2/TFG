package cat.udl.eps.softarch.hello.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


//PERQUE NO PUC POSAR AQUESTA ANOTACIÓ??
//@Entity
public class DofiReport implements Report{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String value;

    public  List<String> questions = new ArrayList<String>();
    public  List<String> values = new ArrayList<String>();


    public DofiReport() {

        this.questions.add("1.Te un nivell d'apnea capaç per realitzar més de 25 metres");
        this.questions.add("2.És capaç de realitzar el viratge de crol");
        this.questions.add("3.És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");
    }


    @Override
    public long getId() { return id; }


    @Override
    public List<String> getQuestions() {

        return this.questions;
    }


    @Override
    public String getValue() { return value;}
    
    @Override
    public void setValue(String value) {
              System.out.println("Value!!-..............++++++............................->"+value);

     this.value = value;        
  }



    @Override
    public List<String> getValues() {

        return this.values;
    }

    @Override
    public void setValues(List<String> values) {

        for( String value : values ){

          System.out.println("Value!!-..........................................->"+value);
        }

        this.values = values;
    }







}