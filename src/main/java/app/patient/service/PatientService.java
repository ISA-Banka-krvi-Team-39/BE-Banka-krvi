package app.patient.service;

import app.patient.model.Patient;
import app.patient.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PatientService implements IPatientService{

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    @Transactional
    public Patient findOne(int id) {
        return patientRepository.findOneByPatientId(id);
    }

    @Override
    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }
}