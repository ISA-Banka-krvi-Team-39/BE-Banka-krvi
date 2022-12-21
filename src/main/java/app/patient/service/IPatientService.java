package app.patient.service;

import app.patient.model.Patient;

public interface IPatientService {

    public Patient findOne(int id);
    public Patient findOneByPersonId(int personId);
    public Patient create(Patient patient);

    public Patient save(Patient patient);
}
