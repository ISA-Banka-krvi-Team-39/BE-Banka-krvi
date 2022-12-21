package app.center.service;

import app.center.model.Center;
import app.center.model.Term;
import app.center.repository.ITermRepository;
import app.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Term> getAll() { return termRepository.findAll();}
}
