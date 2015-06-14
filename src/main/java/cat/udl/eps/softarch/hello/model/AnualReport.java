package cat.udl.eps.softarch.hello.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class AnualReport{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String level;

    public String value;
    //value2
    //value3
    //...value20

    @ElementCollection 
    public  List<String> questions = new ArrayList<String>();
  //  @ElementCollection 
   // public  List<String> values = new ArrayList<String>();
    
    public String comment;


    public AnualReport() {
    }


    
    public long getId() { return id; }

    public void setLevel(String level){ this.level = level;}
    public String getLevel(){ return this.level;}




    public List<String> getQuestions() {

      if(level == "Dofins"){

        this.questions.add("1. Te un nivell d'apnea capaç per realitzar més de 25 metres");
        this.questions.add("2. És capaç de realitzar el viratge de crol");
        this.questions.add("3. És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");
      }
      if(level == "Sardines"){

        this.questions.add("1. Domina l'estil crol amb respiració bilateral");
        this.questions.add("2. Iniciazió en l'estil esquena");
        this.questions.add("3. Te una bona freqüencia en les braçades d'esquena");
      }      
      //if(level == "Taurons"){
      //....
      //}

        return this.questions;
    }


    
    public String getValue() { return value;}
    //getvalue2,3,4...
    //getvalue20..


    public void setValue(String value) {
     this.value = value;        
    }

    //Setvalue2,3,4...
    //setvalue20..

    public void setComment(String comment){ this.comment = comment;}

    
    public String getComment(){ return comment; }



 //   @Override
 //   public List<String> getValues() {

   //     return this.values;
  //  }

   // @Override
   // public void setValues(List<String> values) {

   //     for( String value : values ){

   //       System.out.println("Value!!-..........................................->"+value);
    //    }
//
  //     this.values = values;
   // }







}