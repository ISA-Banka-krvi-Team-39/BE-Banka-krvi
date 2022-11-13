package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import app.person.model.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TermDTO {

    private LocalDateTime dateTime;
    private Set<Person> medicalStaffs = new HashSet<Person>();
    private Integer durationInMinutes;

    private Center center;

    public TermDTO()
    {

    }

    public TermDTO(Term term,Center center)
    {
        this.dateTime = term.getDateTime();
        this.medicalStaffs = term.getMedicalStaffs();
        this.durationInMinutes = term.getDurationInMinutes();
        this.center = center;
    }

    public TermDTO(LocalDateTime dateTime, Set<Person> medicalStaffs, Integer durationInMinutes, Center center)
    {
        this.dateTime = dateTime;
        this.medicalStaffs = medicalStaffs;
        this.durationInMinutes = durationInMinutes;
        this.center = center;
    }

    public LocalDateTime getDateTime(){return dateTime;}

    public Set<Person> getMedicalStaffs(){return medicalStaffs;}

    public Integer getDurationInMinutes(){return durationInMinutes;}



    public void setDateTime(LocalDateTime dateTime){this.dateTime = dateTime; }
    public void setMedicalStaffs(Set<Person> medicalStaffs){this.medicalStaffs = medicalStaffs;}
    public void setDurationInMinutes(Integer durationInMinutes){this.durationInMinutes = durationInMinutes;}
    public void setCenter(Center center){this.center = center;}
}
