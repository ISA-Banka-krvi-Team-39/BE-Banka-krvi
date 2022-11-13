package app.medical_staff.model.service;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface IMedicalStaffService {
    List<MedicalStaff> findAll();
    public List<MedicalStaff> findAllByWorkingCenter(Center workingCenter);

    public MedicalStaff save(MedicalStaff medicalStaff);

    public void delete(MedicalStaff medicalStaff);
    public List<MedicalStaff> findAllMedicalStaff();
}
