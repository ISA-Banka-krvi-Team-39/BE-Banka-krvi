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

    public TermDTO()
    {

    }

    public TermDTO(Term term)
    {
        this.dateTime = term.getDateTime();
        this.medicalStaffs = term.getMedicalStaffs();
        this.durationInMinutes = term.getDurationInMinutes();
    }

    public TermDTO(LocalDateTime dateTime, Set<Person> medicalStaffs, Integer durationInMinutes)
    {
        this.dateTime = dateTime;
        this.medicalStaffs = medicalStaffs;
        this.durationInMinutes = durationInMinutes;
    }

    public LocalDateTime getDateTime(){return dateTime;}

    public Set<Person> getMedicalStaffs(){return medicalStaffs;}

    public Integer getDurationInMinutes(){return durationInMinutes;}

    public void setDateTime(LocalDateTime dateTime){this.dateTime = dateTime; }
    public void setMedicalStaffs(Set<Person> medicalStaffs){this.medicalStaffs = medicalStaffs;}
    public void setDurationInMinutes(Integer durationInMinutes){this.durationInMinutes = durationInMinutes;}
}
