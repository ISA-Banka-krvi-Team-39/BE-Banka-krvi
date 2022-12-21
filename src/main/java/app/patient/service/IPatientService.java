package app.patient.service;

import app.patient.model.Patient;

import java.util.List;

public interface IPatientService {

    public Patient findOne(int id);
    public Patient findOneByPersonId(int personId);
    public Patient create(Patient patient);

    public Patient save(Patient patient);
    public List<Patient> findAll();
}
