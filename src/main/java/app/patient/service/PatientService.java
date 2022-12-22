package app.patient.service;

import app.email.model.EmailDetails;
import app.email.service.IEmailService;
import app.patient.model.Patient;
import app.patient.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.List;

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
    @Transactional
    public Patient findOneByPersonId(int personId) {return patientRepository.findOneByPerson(personId);}
    @Override
    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    @Override
    public Patient save(Patient patient){ return patientRepository.save(patient);}
}
