package app.canceled_term.service;

import app.canceled_term.model.CanceledTerm;

public interface ICanceledTermService {
    public CanceledTerm create(CanceledTerm term);
    public Boolean findOneByPatientAndTerm(Integer termId, Integer patientId);
}
