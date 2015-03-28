package cat.udl.eps.softarch.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;


public interface SwimmerGroupRepository extends JpaRepository<SwimmerGroup, Long> {

    SwimmerGroup findSwimmerGroupBySessionHour(@Param("sessionHour") String sessionHour);
    SwimmerGroup findSwimmerGroupByLevel(@Param("level") String level);
}
