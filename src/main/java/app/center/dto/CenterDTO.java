package app.center.dto;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.person.dto.PersonDTO;
import app.shared.model.Address;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CenterDTO {

    private Integer centerId;
    private String name;
    private String description;
    private Float avgGrade;

    private Address address;

    private Set<PersonDTO> workingMedicalStaff = new HashSet<PersonDTO>();

    public CenterDTO()
    {

    }

    public CenterDTO(Center center)
    {
        this.centerId = center.getCenterId();
        this.name = center.getName();
        this.description = center.getDescription();
        this.avgGrade = center.getAvgGrade();
        this.address = center.getAddress();

    }

    public CenterDTO(Center center,List<MedicalStaff> medicalStaffList)
    {
        this.centerId = center.getCenterId();
        this.name = center.getName();
        this.description = center.getDescription();
        this.avgGrade = center.getAvgGrade();
        this.workingMedicalStaff = mapPatientsToDto(center,medicalStaffList);
        this.address = center.getAddress();

    }

    public CenterDTO(String name, String description, Float avg_grade,Set<PersonDTO> workingMedicalStaff,Address address,Integer centerId)
    {
        this.centerId = centerId;
        this.name = name;
        this.description = description;
        this.avgGrade = avg_grade;
        this.workingMedicalStaff = workingMedicalStaff;
        this.address = address;
    }
    public String getName()
    {
        return name;
    }

    public Integer getCenterId(){return centerId;}
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
    public Float getAvgGrade()
    {
        return avgGrade;
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
