package app.center.service;

import app.center.model.State;
import app.center.model.Term;
import app.center.repository.ITermRepository;
import app.person.model.Person;
import app.person.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TermService implements ITermService {
    @Autowired
    private ITermRepository termRepository;
    @Autowired
    private IPersonService personService;

    @Override
    @Transactional(readOnly = false)
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
    public void cancelTermById(int termId) {
        termRepository.cancelTermById(termId);
    }


    @Override
    public List<Term> getAllPatientsTerms(int id) {
        return termRepository.getAllPatientsTerms(id);
    }

    @Override
    public List<Term> getAllAdminTerms(int id) {
        List<Term> termList = termRepository.findAll();
        List<Term> retTermList = new ArrayList<>();
        for(Term term : termList)
        {
            if(term.getCenter().getCenterId() == id)
                retTermList.add(term);
        }

        return retTermList;
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
    @Transactional(readOnly = false)
    public Term save(Term term){ return termRepository.save(term);}

    @Override
    @Transactional(readOnly = false)
    public void schedule(Term term, Integer personId) {
        Person person = personService.findOne(personId);
        term.setState(State.PENDING);
        term.setBloodDonors(person);
        save(term);
    }

    @Override
    public boolean checkTerm(LocalDateTime date,int duration) {
        if(date.compareTo(LocalDateTime.now().plusDays(1)) < 0) return false;
        for(Term term : termRepository.getTermsForDate(date.plusDays(-1),date.plusDays(1)))
        {
            if((date.compareTo(term.getDateTime()) > 0 && date.compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) < 0)
                || (date.plusMinutes(duration).compareTo(term.getDateTime()) > 0 && date.plusMinutes(duration).compareTo(term.getDateTime().plusMinutes(term.getDurationInMinutes())) < 0))
            return false;
        }
        return true;
    }

    @Override
    public List<Term> getTermsByDateTime(LocalDateTime localDateTime) {
        return termRepository.getTermsByDateTime(localDateTime.plusHours(-1),localDateTime.plusHours(1));
    }
}
