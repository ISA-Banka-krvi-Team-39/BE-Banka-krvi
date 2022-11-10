package app.center.dto;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.medical_staff.model.service.IMedicalStaffService;
import app.person.dto.PersonDTO;
import app.person.model.Person;
import app.person.repository.IPersonRepository;
import app.person.service.IPersonService;
import app.shared.model.Address;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CenterDTO {
    private String name;
    private String description;
    private Float avg_grade;

    private Address address;

    private Set<PersonDTO> workingMedicalStaff = new HashSet<PersonDTO>();

    public CenterDTO()
    {

    }

    public CenterDTO(Center center,List<MedicalStaff> medicalStaffList)
    {
        this.name = center.getName();
        this.description = center.getDescription();
        this.avg_grade = center.getAvg_grade();
        this.workingMedicalStaff = mapPatientsToDto(center,medicalStaffList);
        this.address = center.getAddress();

    }

    public CenterDTO(String name, String description, Float avg_grade,Set<PersonDTO> workingMedicalStaff,Address address)
    {
        this.name = name;
        this.description = description;
        this.avg_grade = avg_grade;
        this.workingMedicalStaff = workingMedicalStaff;
        this.address = address;
    }
    public String getName()
    {
        return name;
    }
    public Address getAddress()
    {
        return address;
    }
    public Set<PersonDTO> getWorkingMedicalStaff()
    {
        return workingMedicalStaff;
    }
    public String getDescription()
    {
        return description;
    }
    public Float getAvg_grade()
    {
        return avg_grade;
    }
    public Set<PersonDTO> mapPatientsToDto(Center center,List<MedicalStaff> medicalStaffList)
    {
        Set<PersonDTO> personDtos = new HashSet<PersonDTO>();
        for(MedicalStaff p : medicalStaffList)
        {
            personDtos.add(new PersonDTO(p.getPerson().getPersonId(),p.getPerson().getName(),p.getPerson().getSurname(),p.getPerson().getPersonType(),p.getPerson().getAddress(),p.getPerson().getPhoneNumber(),p.getPerson().getSchool()));
        }
        return personDtos;
    }

}
