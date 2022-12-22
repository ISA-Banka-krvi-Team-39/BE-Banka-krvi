package app.center.service;

import app.center.model.Term;
import app.patient.model.Patient;
import org.springframework.data.jpa.repository.Query;

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
}
