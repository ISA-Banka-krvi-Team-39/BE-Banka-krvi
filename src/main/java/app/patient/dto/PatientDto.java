package app.patient.dto;

import app.appointment.model.Appointment;
import app.patient.model.BloodType;
import app.person.model.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class PatientDto {


    private Integer patientId;

    private Integer personId;

    private Integer termId;

    private String name;

    private String surname;

    public PatientDto()
    {

    }
    public PatientDto(Integer patientId, Integer personId, String name, String surname) {
        this.patientId = patientId;
        this.personId = personId;
        this.name = name;
        this.surname = surname;
    }

    public PatientDto(Integer patientId, Integer personId, Integer termId, String name, String surname) {
        this.patientId = patientId;
        this.personId = personId;
        this.termId = termId;
        this.name = name;
        this.surname = surname;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
