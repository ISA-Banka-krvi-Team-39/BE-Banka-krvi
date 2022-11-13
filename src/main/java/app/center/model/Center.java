package app.center.model;

import app.center.dto.CreateCenterDTO;
import app.medical_staff.model.MedicalStaff;
import app.shared.model.Address;
import app.user.dtos.CreateUserDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Center {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer centerId;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @Column(name="description", unique=false, nullable=false)
    private String description;

    @Column(name="avg_grade", unique=false, nullable=false)
    private Float avgGrade;

    @OneToMany(mappedBy = "termId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Term> terms = new HashSet<Term>();

    @OneToMany(mappedBy = "bloodId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BloodBag> bloodBags = new HashSet<BloodBag>();

    @OneToMany(mappedBy = "medicalStaffId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicalStaff> workingMedicalStaff = new HashSet<MedicalStaff>();

    public Center()
    {
    }

    public Center(Integer centerId, String name, Address address, String description, Float avg_grade, Set<Term> terms, Set<MedicalStaff> workingMedicalStaff) {
        this.centerId = centerId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.avgGrade = avg_grade;
        this.terms = terms;
        this.workingMedicalStaff = workingMedicalStaff;
    }

    public Center(CreateCenterDTO centerDTO) {
        this.name = centerDTO.getName();
        this.description= centerDTO.getDescription();
        this.address = centerDTO.getAddress();
        this.avgGrade = centerDTO.getAvgGrade();
        this.workingMedicalStaff = centerDTO.getWorkingMedicalStaff();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Center center = (Center) o;
        return Objects.equals(centerId, center.centerId) && Objects.equals(name, center.name) && Objects.equals(address, center.address) && Objects.equals(description, center.description) && Objects.equals(avgGrade, center.avgGrade) && Objects.equals(terms, center.terms) && Objects.equals(workingMedicalStaff, center.workingMedicalStaff);
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvgGrade(Float avg_grade) {
        this.avgGrade = avg_grade;
    }

    public void setTerms(Set<Term> terms) {
        this.terms = terms;
    }

    public void setWorkingMedicalStaff(Set<MedicalStaff> workingMedicalStaff) {
        this.workingMedicalStaff = workingMedicalStaff;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public Float getAvgGrade() {
        return avgGrade;
    }

    public Set<Term> getTerms() {
        return terms;
    }

    public Set<MedicalStaff> getWorkingMedicalStaff() {
        return workingMedicalStaff;
    }
}
