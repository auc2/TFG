package cat.udl.eps.softarch.hello.model;

import java.util.List;


public interface Report{


    long getId();

    List<String> getQuestions();

   // public List<String> getValues();
   // public void setValues(List<String> values);




    String getValue();
    void setValue(String value);

    void setComment(String comment);
    String getComment();

}