package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.model.Teacher;
import java.util.ArrayList;
import java.util.List;



public interface SwimmerGroupRepository extends JpaRepository<SwimmerGroup, Long> {

    SwimmerGroup findSwimmerGroupBySessionHour(@Param("sessionHour") String sessionHour);
    List<SwimmerGroup> findSwimmerGroupByTeacher(@Param("teacher") Teacher teacher);

}
