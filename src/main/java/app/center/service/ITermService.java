package app.center.service;

import app.center.dto.TermDTO;
import app.center.model.Term;
import java.time.LocalDateTime;
import java.util.List;

public interface ITermService {
    public Term create(Term term);
    public Term findOne(Integer id);
    public List<Term> getAll();
    public List<Term> getAllFree();
    public List<Term> getAllPatientsTerms(int id);
    public Boolean canPatientDonate(int personId);
    public Boolean canTermBeCanceled(int termId);
    public Term save(Term term);
    public void schedule(Term term, Integer personId);
    public boolean checkTerm(LocalDateTime date,int duration);
    public List<Term> getTermsByDateTime(LocalDateTime localDateTime);
}
