package app.medical_staff.model.service;

import app.center.model.Center;
import app.center.repository.ICenterRepository;
import app.medical_staff.model.MedicalStaff;
import app.medical_staff.model.repository.IMedicalStaffRepository;
import app.person.model.Person;
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
    @Override
    public MedicalStaff save(MedicalStaff medicalStaff){return medicalStaffRepository.save(medicalStaff);}

    @Override
    public void delete(MedicalStaff medicalStaff){ medicalStaffRepository.delete(medicalStaff);}

    @Override
    public List<MedicalStaff> findAllMedicalStaff(){return medicalStaffRepository.findAllMedicalStaff();}

}
