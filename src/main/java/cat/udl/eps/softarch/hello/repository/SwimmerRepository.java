package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.Swimmer;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import java.util.ArrayList;
import java.util.List;


public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {

    Swimmer findSwimmerBySwimmername(@Param("swimmername") String swimmername);
    Swimmer findSwimmerByEmail(@Param("email") String email);
    List<Swimmer> findSwimmerByGroup(@Param("group") SwimmerGroup swimmerGroup);
}
