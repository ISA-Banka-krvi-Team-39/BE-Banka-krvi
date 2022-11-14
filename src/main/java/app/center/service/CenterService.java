package app.center.service;

import app.center.model.Center;
import app.center.repository.ICenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenterService implements ICenterService {
    @Autowired
    private ICenterRepository centerRepository;

    @Override
    public Page<Center> getAll(Pageable pageable,String name,String city,int gradeFilterFrom,int gradeFilterTo) {return centerRepository.getAll(pageable,name,city,gradeFilterFrom,gradeFilterTo);}

    public Center findOne(Integer id) {
        return centerRepository.findOneByCenterId(id);
    }


    public Center save(Center center) {
        return centerRepository.save(center);
    }
    
    public List<Center> getAll() {
        return centerRepository.findAll();
    }

    @Override
    public Center create(Center center) { return centerRepository.save(center);}


}
