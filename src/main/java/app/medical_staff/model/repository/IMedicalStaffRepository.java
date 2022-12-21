package app.medical_staff.model.repository;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.patient.model.Patient;
import app.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {
    public List<MedicalStaff> findAllByWorkingCenter(Center workingCenter);

    @Query("select m from MedicalStaff m where m.workingCenter.centerId=?1")
    public List<MedicalStaff> findAllMedicalStaff(int centerId);
    @Query("select m from MedicalStaff m where m.person.personId = ?1")
    public MedicalStaff findOneByPerson(int personId);

}

