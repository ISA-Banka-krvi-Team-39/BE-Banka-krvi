package app.center.service;

import app.center.model.Center;
import app.center.model.Term;
import app.center.repository.ICenterRepository;
import app.center.repository.ITermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CenterService implements ICenterService {
    @Autowired
    private ICenterRepository centerRepository;
    @Autowired
    private ITermRepository termRepository;

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

    @Override
    public List<Center> getTermsByDateTime(LocalDateTime localDateTime,int duration) {
        List<Center> allCenters = centerRepository.findAll();
        List<Center> freeCenters = new ArrayList<>();
        boolean freee = true;
        for (Center center : allCenters) {
            freee = true;
            for (Term term : termRepository.checkIfCenterFree(localDateTime.plusDays(-1),localDateTime.plusDays(1),center.getCenterId()))
                if((localDateTime.compareTo(term.getDateTime()) >= 0 && localDateTime.compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) <= 0)
                        || (localDateTime.plusMinutes(duration).compareTo(term.getDateTime()) >= 0 && localDateTime.plusMinutes(duration).compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) <= 0)
                || (localDateTime.compareTo(term.getDateTime()) <= 0 && localDateTime.plusMinutes(duration).compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) >= 0))
                freee = false;
            if (freee) freeCenters.add(center);
        }
        return freeCenters;
    }


}
