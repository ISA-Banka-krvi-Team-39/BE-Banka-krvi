package app.center.service;

import app.center.model.Center;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ICenterService {


    public Center findOne(Integer id);
    public Center save(Center center);
    public List<Center> getAll();

    public Page<Center> getAll(Pageable pageable,String name,String city,int gradeFilterFrom,int gradeFilterTo);

    public Center create(Center center);

}
