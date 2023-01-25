package app.center.repository;

import app.center.model.State;
import app.center.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ITermRepository extends JpaRepository<Term, Integer> {
    public Term findOneByTermId(Integer termId);
    public List<Term> findAll();
    @Query("select t from Term t where t.state = 0 and t.dateTime > CURRENT_DATE")
    public List<Term> findFree();

    @Transactional
    @Modifying
    @Query("delete from Term where termId = :termId")
    public void cancelTermById(@Param("termId")int termId);

    @Query("select t from Term t where t.dateTime between :startDate and :endDate and t.bloodDonor.personId = :personId")
    public List<Term> getTermsByPatientInPlusMinus6Months(@Param("personId")int personId
            ,@Param("startDate")LocalDateTime startDate
            ,@Param("endDate")LocalDateTime endDate);

    @Query("select t from Term t where t.bloodDonor.personId = :personId")
    public List<Term> getAllPatientsTerms(@Param("personId")int personId);
    @Query("select t from Term t where t.center.centerId = :centerId")
    List<Term> getAllAdminTerms(@Param("centerId")int centerId);
    @Query("select t from Term t where t.dateTime between :startDate and :endDate")
    public List<Term> getTermsForDate(@Param("startDate")LocalDateTime startDate
            ,@Param("endDate")LocalDateTime endDate);
    @Query("select t from Term t where t.dateTime between :startDate and :endDate and t.state = 0")
    public List<Term> getTermsByDateTime(@Param("startDate")LocalDateTime startDate
            ,@Param("endDate")LocalDateTime endDate);
}
