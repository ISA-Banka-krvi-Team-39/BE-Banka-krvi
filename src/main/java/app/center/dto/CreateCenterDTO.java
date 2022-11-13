package app.center.dto;

import app.center.model.Center;
import app.medical_staff.model.MedicalStaff;
import app.person.dto.PersonDTO;
import app.shared.model.Address;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateCenterDTO {

    private Integer centerId;
    private String name;
    private String description;
    private Float avgGrade;
    private Address address;
    private Set<MedicalStaff> workingMedicalStaff = new HashSet<MedicalStaff>();


    public CreateCenterDTO()
    {
    }

    public CreateCenterDTO(String name, String description, Float avgGrade, Address address, Set<MedicalStaff> workingMedicalStaff) {
        this.name = name;
        this.description = description;
        this.avgGrade = avgGrade;
        this.address = address;
        this.workingMedicalStaff = workingMedicalStaff;
    }
    public CreateCenterDTO(Integer id,String name, String description, Float avgGrade, Address address, Set<MedicalStaff> workingMedicalStaff) {
        this.name = name;
        this.description = description;
        this.avgGrade = avgGrade;
        this.address = address;
        this.workingMedicalStaff = workingMedicalStaff;
        this.centerId = id;
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
    public Set<MedicalStaff> getWorkingMedicalStaff()
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


}
