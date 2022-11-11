package app.patient.repository;

import app.patient.model.Patient;
import app.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findOneByPatientId(int id);
}
