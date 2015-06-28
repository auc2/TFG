package cat.udl.eps.softarch.hello.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class AnualReport{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String level;

    private String value;

    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
    private String value7;
    private String value8;
    private String value9;
    private String value10;


    @ElementCollection(fetch=FetchType.EAGER)
    private  List<String> questions = new ArrayList<String>();
  //  @ElementCollection(fetch=FetchType.EAGER)
  //  public  List<String> values = new ArrayList<String>();
    
    private String comment;


    public AnualReport() {
    }


    
    public long getId() { return id; }




    public List<String> getQuestions() {

      if(this.level == "Sardines"){

        this.questions.add("1. Domina l'estil crol amb respiració bilateral");
        this.questions.add("2. Iniciazió en l'estil esquena");
        this.questions.add("3. Te una bona freqüencia en les braçades d'esquena");
        this.questions.add("4. Domina l'estil crol amb respiració bilateral");
        this.questions.add("5. Iniciazió en l'estil esquena");
        this.questions.add("6. Te una bona freqüencia en les braçades d'esquena");
        this.questions.add("7. Domina l'estil crol amb respiració bilateral");
        this.questions.add("8. Iniciazió en l'estil esquena");
        this.questions.add("9. Te una bona freqüencia en les braçades d'esquena");
        this.questions.add("10. Domina l'estil crol amb respiració bilateral");
      }
      if(this.level == "Dofins"){

        this.questions.add("1. Te un nivell d'apnea capaç per realitzar més de 25 metres");
        this.questions.add("2. És capaç de realitzar el viratge de crol");
        this.questions.add("3. És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");
        this.questions.add("4. Te un nivell d'apnea capaç per realitzar més de 25 metres");
        this.questions.add("5. És capaç de realitzar el viratge de crol");
        this.questions.add("6. És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");        
        this.questions.add("7. Te un nivell d'apnea capaç per realitzar més de 25 metres");
        this.questions.add("8. És capaç de realitzar el viratge de crol");
        this.questions.add("9. És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");
        this.questions.add("10. És capaç d'aguantar-se inmovil en poscició prono durant 10 segons.");

      }
          
      //if(level == "Taurons"){
      //....
      //}

        return this.questions;
    }


       public String getValue() { return value;}

   public String getValue1() { return value1;}
   public String getValue2() { return value2;}
   public String getValue3() { return value3;}
   public String getValue4() { return value4;}
   public String getValue5() { return value5;}
   public String getValue6() { return value6;}
   public String getValue7() { return value7;}
   public String getValue8() { return value8;}
   public String getValue9() { return value9;}
   public String getValue10() { return value10;}


    public void setValue(String value) { this.value = value; }

        public void setValue1(String value1) { this.value1 = value1;}
        public void setValue2(String value2) { this.value2 = value2;}
        public void setValue3(String value3) { this.value3 = value3;}
        public void setValue4(String value4) { this.value4 = value4;}
        public void setValue5(String value5) { this.value5 = value5;}
        public void setValue6(String value6) { this.value6 = value6;}
        public void setValue7(String value7) { this.value7 = value7;}
        public void setValue8(String value8) { this.value8 = value8;}
        public void setValue9(String value9) { this.value9 = value9;}
        public void setValue10(String value10) { this.value10 = value10;
        }


    public void setLevel(String level){ this.level = level;}
    public String getLevel(){ return level;}


    public void setComment(String comment){ this.comment = comment;}

    
    public String getComment(){ return comment; }




    public List<String> getValues() {

      List<String> values = new ArrayList<String>();

      values.add(getValue1());
      values.add(getValue2());
      values.add(getValue3());
      values.add(getValue4());
      values.add(getValue5());
      values.add(getValue6());
      values.add(getValue7());
      values.add(getValue8());
      values.add(getValue9());
      values.add(getValue10());

        return values;
    }

   // @Override
   // public void setValues(List<String> values) {

   //     for( String value : values ){

   //       System.out.println("Value!!-..........................................->"+value);
    //    }
//
  //     this.values = values;
   // }







}