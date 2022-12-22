package app.center.service;

import app.center.model.Term;
import app.center.repository.ITermRepository;
import app.patient.model.Patient;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TermService implements ITermService {
    @Autowired
    private ITermRepository termRepository;

    @Override
    public Term create(Term term) {return termRepository.save(term); }

    public Term findOne(Integer id) {
        return termRepository.findOneByTermId(id);
    }

    @Override
    public List<Term> getAll() { return termRepository.findAll(); }
    @Override
    public List<Term> getAllFree() {
        return termRepository.findFree();
    }

    @Override
    public List<Term> getAllPatientsTerms(int id) {
        return termRepository.getAllPatientsTerms(id);
    }

    @Override
    public Boolean canPatientDonate(int personId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.plusMonths(-6);
        LocalDateTime end = now.plusMonths(6);
        System.out.println(termRepository.getTermsByPatientInPlusMinus6Months(personId,start,end).size());
        return termRepository.getTermsByPatientInPlusMinus6Months(personId,start,end).size() == 0;
    }

    @Override
    public Term save(Term term){ return termRepository.save(term);}
}
