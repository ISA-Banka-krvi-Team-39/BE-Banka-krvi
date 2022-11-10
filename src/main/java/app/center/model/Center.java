package app.center.model;

import app.medical_staff.model.MedicalStaff;
import app.person.dto.PersonDTO;
import app.shared.model.Address;
import app.person.model.Person;

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
    private Float avg_grade;

    @OneToMany(mappedBy = "termId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Term> terms = new HashSet<Term>();

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
        this.avg_grade = avg_grade;
        this.terms = terms;
        this.workingMedicalStaff = workingMedicalStaff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Center center = (Center) o;
        return Objects.equals(centerId, center.centerId) && Objects.equals(name, center.name) && Objects.equals(address, center.address) && Objects.equals(description, center.description) && Objects.equals(avg_grade, center.avg_grade) && Objects.equals(terms, center.terms) && Objects.equals(workingMedicalStaff, center.workingMedicalStaff);
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

    public void setAvg_grade(Float avg_grade) {
        this.avg_grade = avg_grade;
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

    public Float getAvg_grade() {
        return avg_grade;
    }

    public Set<Term> getTerms() {
        return terms;
    }

    public Set<MedicalStaff> getWorkingMedicalStaff() {
        return workingMedicalStaff;
    }
}
