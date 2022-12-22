package app.medical_staff.service;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.patient.model.Patient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


public interface IMedicalStaffService {
    List<MedicalStaff> findAll();
    public List<MedicalStaff> findAllByWorkingCenter(Center workingCenter);
    public MedicalStaff save(MedicalStaff medicalStaff);
    public void delete(MedicalStaff medicalStaff);
    public MedicalStaff findOneByPersonId(int personId);
    public List<MedicalStaff> findAllMedicalStaff(int centerId);
}
