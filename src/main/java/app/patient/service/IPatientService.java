package app.patient.service;

import app.patient.model.Patient;

public interface IPatientService {

    public Patient findOne(int id);
    public Patient create(Patient patient);
}
