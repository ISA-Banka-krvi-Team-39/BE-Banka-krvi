package app.patient.repository;

import app.patient.model.Patient;
import app.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findOneByPatientId(int id);
    @Query("select p from Patient p where p.person.personId = ?1")
    public Patient findOneByPerson(int personId);
}
