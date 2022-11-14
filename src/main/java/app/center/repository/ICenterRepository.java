package app.center.repository;

import app.center.model.Center;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICenterRepository extends JpaRepository<Center, Integer> {
    public Center findOneByCenterId(Integer centerId);

    @Query("select c from Center c where lower(c.name) like %?1% and lower(c.address.city) like %?2% " +
            "and c.avgGrade >= ?3 and c.avgGrade <= ?4")
    public Page<Center> getAll(Pageable pageable,String name,String city,float gradeFilterFrom,float gradeFilterTo);
    
}
