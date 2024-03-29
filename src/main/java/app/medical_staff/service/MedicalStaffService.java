package app.medical_staff.service;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.medical_staff.repository.IMedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    @Transactional
    public MedicalStaff findOneByPersonId(int personId) {
        return medicalStaffRepository.findOneByPerson(personId);
    }
    @Override
    public MedicalStaff findCenterIdByPersonId(int id){return medicalStaffRepository.findOneByPerson(id);}
    @Override
    public List<MedicalStaff> findAllMedicalStaff(int centerId){return medicalStaffRepository.findAllMedicalStaff(centerId);}

}
