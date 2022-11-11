package app.medical_staff.model.service;

import app.center.model.Center;
import app.center.repository.ICenterRepository;
import app.medical_staff.model.MedicalStaff;
import app.medical_staff.model.repository.IMedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MedicalStaffService implements IMedicalStaffService {
    @Autowired
    private IMedicalStaffRepository medicalStaffRepository;


    public List<MedicalStaff> findAll() {
        return medicalStaffRepository.findAll();
    }
    public List<MedicalStaff> findAllByWorkingCenter(Center workingCenter) {
        return medicalStaffRepository.findAllByWorkingCenter(workingCenter);
    }

}