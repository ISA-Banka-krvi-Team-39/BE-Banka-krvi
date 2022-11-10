package app.medical_staff.model;

import app.center.model.Center;
import app.person.model.Person;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicalStaffId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centerId")
    private Center workingCenter;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    private Person person;

    public MedicalStaff() {
    }

    public MedicalStaff(Integer medicalStaffId, Center workingCenter, Person person) {
        this.medicalStaffId = medicalStaffId;
        this.workingCenter = workingCenter;
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalStaff that = (MedicalStaff) o;
        return Objects.equals(medicalStaffId, that.medicalStaffId) && Objects.equals(workingCenter, that.workingCenter) && Objects.equals(person, that.person);
    }

    public void setMedicalStaffId(Integer medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public void setWorkingCenter(Center workingCenter) {
        this.workingCenter = workingCenter;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getMedicalStaffId() {
        return medicalStaffId;
    }

    public Center getWorkingCenter() {
        return workingCenter;
    }

    public Person getPerson() {
        return person;
    }
}
