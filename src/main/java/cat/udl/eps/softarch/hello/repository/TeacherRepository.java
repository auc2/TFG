package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.Teacher;



public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findTeacherByTeachername(@Param("teachername") String teachername);
    Teacher findTeacherByEmail(@Param("email") String email);
    
    /*eacher findTeacherBySurname(@Param("surname") String surname);
    Teacher findTeacherByCity(@Param("city") String city);
    Teacher findTeacherByTelephone(@Param("telephone") String telephone);*/
}