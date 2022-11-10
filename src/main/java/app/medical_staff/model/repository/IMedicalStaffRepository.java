package app.medical_staff.model.repository;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {
    public List<MedicalStaff> findAllByWorkingCenter(Center workingCenter);

}

