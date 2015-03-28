package cat.udl.eps.softarch.hello.service;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;


public interface SwimmerGroupService {

    SwimmerGroup getSwimmerGroup(Long userId);

    SwimmerGroup addSwimmerGroup(SwimmerGroup group);

  //  SwimmerGroup deleteSwimmerGroup(SwimmerGroup group);

}
