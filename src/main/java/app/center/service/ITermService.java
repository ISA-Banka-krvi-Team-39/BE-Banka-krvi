package app.center.service;

import app.center.dto.TermDTO;
import app.center.model.Term;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDateTime;
import java.util.List;

public interface ITermService {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Term create(Term term);
    public Term findOne(Integer id);
    public List<Term> getAll();
    public List<Term> getAllFree();
    public List<Term> getAllPatientsTerms(int id);
    public List<Term> getAllAdminTerms(int id);
    public Boolean canPatientDonate(int personId);
    public Boolean canTermBeCanceled(int termId);
    public Term save(Term term);
    public void schedule(Term term, Integer personId);
    public boolean checkTerm(LocalDateTime date,int duration);
    public void cancelTermById(int termId);

    int getTermsMonthly(LocalDateTime localDateTime);

    int getTerms3Months(LocalDateTime localDateTime);

    int getTermsYearly(LocalDateTime localDateTime);
}
