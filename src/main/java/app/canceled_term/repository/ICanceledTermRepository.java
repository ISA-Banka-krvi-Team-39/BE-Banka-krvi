package app.canceled_term.repository;

import app.canceled_term.model.CanceledTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICanceledTermRepository extends JpaRepository<CanceledTerm, Integer> {
    @Query("select t from CanceledTerm t where t.patientId = :patientId and t.termId = :termId")
    public CanceledTerm findOneByPatientAndTerm(@Param("patientId")int patientId, @Param("termId")int termId);
}
