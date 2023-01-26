package app.patient.service;

import app.appointment.model.Appointment;
import app.email.model.EmailDetails;
import app.email.service.IEmailService;
import app.patient.model.Patient;
import app.patient.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    public Integer getPatientPenals(int id) {
        return findOne(id).getPenal();
    }
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    @Scheduled(cron = "${greeting.cron}")
    protected void resetPenals(){
        List<Patient> patients = findAll();
        for(Patient patient : patients){
            patient.setPenal(0);
            save(patient);
        }
    }
    @Override
    public Patient save(Patient patient){ return patientRepository.save(patient);}
    @Override
    public int findPatientByPersonId(int personId)
    {
        int patientId = -1;
        List<Patient> patientList = patientRepository.findAll();
        for(Patient patient : patientList)
        {
            if(patient.getPerson().getPersonId() == personId)
            {
                patientId = patient.getPatientId();
                break;
            }
        }
        return patientId;
    }
}
