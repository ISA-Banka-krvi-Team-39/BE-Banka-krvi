package app.medical_staff.model.repository;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {
    public List<MedicalStaff> findAllByWorkingCenter(Center workingCenter);

    @Query("select p from MedicalStaff p where p.workingCenter.centerId=1")
    public List<MedicalStaff> findAllMedicalStaff();

}

