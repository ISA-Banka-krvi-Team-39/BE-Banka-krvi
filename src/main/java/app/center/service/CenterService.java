package app.center.service;

import app.center.model.Center;
import app.center.repository.ICenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CenterService implements ICenterService {
    @Autowired
    private ICenterRepository centerRepository;


    public Center findOne(Integer id) {
        return centerRepository.findOneByCenterId(id);
    }
}
