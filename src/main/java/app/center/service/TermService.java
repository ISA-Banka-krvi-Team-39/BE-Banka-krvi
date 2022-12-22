package app.center.service;

import app.center.dto.CreateTermDTO;
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
        return termRepository.getTermsByPatientInPlusMinus6Months(personId,start,end).size() == 0;
    }

    @Override
    public Boolean canTermBeCanceled(int termId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        Term term = findOne(termId);
        return term.getDateTime().isBefore(tomorrow) || term.getDateTime().isEqual(tomorrow);
    }

    @Override
    public Term save(Term term){ return termRepository.save(term);}

    @Override
    public boolean checkTerm(LocalDateTime date,int duration) {
        if(date.compareTo(LocalDateTime.now()) < 0) return false;
        for(Term term : termRepository.getTermsForDate(date.plusDays(-1),date.plusDays(1)))
        {
            if((date.compareTo(term.getDateTime()) > 0 && date.compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) < 0)
                || (date.plusMinutes(duration).compareTo(term.getDateTime()) > 0 && date.plusMinutes(duration).compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) < 0))
            return false;
        }
        return true;
    }
}
