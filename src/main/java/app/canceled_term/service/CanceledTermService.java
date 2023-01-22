package app.canceled_term.service;

import app.canceled_term.model.CanceledTerm;
import app.canceled_term.repository.ICanceledTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanceledTermService implements ICanceledTermService{

    @Autowired
    private ICanceledTermRepository canceledTermRepository;
    
    @Override
    public CanceledTerm create(CanceledTerm term) {
        return canceledTermRepository.save(term);
    }

    @Override
    public Boolean findOneByPatientAndTerm(Integer termId, Integer patientId) {
        return canceledTermRepository.findOneByPatientAndTerm(patientId, termId) != null;
    }
}
