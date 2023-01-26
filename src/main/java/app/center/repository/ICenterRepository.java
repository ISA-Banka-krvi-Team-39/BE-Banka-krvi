package app.center.repository;

import app.center.model.Center;
import app.center.model.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ICenterRepository extends JpaRepository<Center, Integer> {
    public Center findOneByCenterId(Integer centerId);

    @Query("select c from Center c where lower(c.name) like %?1% and lower(c.address.city) like %?2% " +
            "and c.avgGrade >= ?3 and c.avgGrade <= ?4")
    public Page<Center> getAll(Pageable pageable,String name,String city,float gradeFilterFrom,float gradeFilterTo);

    @Query("select t from Term t where t.dateTime between :startDate and :endDate and t.state = 0")
    public List<Center> getTermsByDateTime(@Param("startDate") LocalDateTime startDate
            , @Param("endDate")LocalDateTime endDate);
    
}
