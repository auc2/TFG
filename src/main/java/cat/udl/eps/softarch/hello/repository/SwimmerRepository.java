package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.Swimmer;


public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {

    Swimmer findSwimmerBySwimmername(@Param("swimmername") String swimmername);
    Swimmer findSwimmerByEmail(@Param("email") String email);
}
