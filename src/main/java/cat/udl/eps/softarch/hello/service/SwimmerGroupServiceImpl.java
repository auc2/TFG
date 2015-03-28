package cat.udl.eps.softarch.hello.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import cat.udl.eps.softarch.hello.model.SwimmerGroup;
import cat.udl.eps.softarch.hello.repository.SwimmerGroupRepository;


@Service
public class SwimmerGroupServiceImpl implements SwimmerGroupService {
    final Logger logger = LoggerFactory.getLogger(SwimmerGroupServiceImpl.class);

    @Autowired
    SwimmerGroupRepository     swimmerGroupRepository;

    @Transactional(readOnly = true)
    @Override
    public SwimmerGroup getSwimmerGroup(Long userId) {
        SwimmerGroup group = swimmerGroupRepository.findOne(userId);
        logger.info("SwimmerGroup level {}", group.getLevel());
        return group;
    }

   @Transactional(readOnly = true)
    @Override
    public SwimmerGroup addSwimmerGroup(SwimmerGroup group){
        swimmerGroupRepository.save(group);
        return group;
    }
  
}
