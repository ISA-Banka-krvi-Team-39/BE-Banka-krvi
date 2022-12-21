package app.patient.model;

import app.appointment.model.Appointment;
import app.person.model.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;

    @OneToMany
    @JoinColumn(name="patient_id")
    private Set<Appointment> appointments = new HashSet<>();
    @Column(name="bloodType", unique=false, nullable=false)
    private BloodType bloodType;
    
    @Column(name="points", unique=false, nullable=false)
    private Integer points;

    public Patient() {

    }

    public Patient(Person person, Integer points,BloodType bloodType) {
        this.person = person;
        this.points = points;
        this.bloodType = bloodType;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public BloodType getBloodType()
    {
        return bloodType;
    }
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
