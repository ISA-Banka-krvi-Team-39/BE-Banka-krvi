package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import app.patient.dto.PatientDTO;
import app.person.model.Person;
import app.person.dto.PersonDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TermDTO {

    private Integer termId;
    private LocalDateTime dateTime;
    private Set<Person> medicalStaffs = new HashSet<>();

    private PersonDTO bloodDonor;
    private Integer durationInMinutes;

    private CenterDTO center;

    public TermDTO()
    {

    }

    public TermDTO(Term term,Center center)
    {
        this.dateTime = term.getDateTime();

        this.durationInMinutes = term.getDurationInMinutes();
        this.center = new CenterDTO(center);
    }

    public TermDTO(Term term)
    {
        this.termId = term.getTermId();
        this.dateTime = term.getDateTime();
        this.bloodDonor = new PersonDTO(term.getBloodDonor());
        this.durationInMinutes = term.getDurationInMinutes();
        this.center = new CenterDTO(term.getCenter());
    }

    public TermDTO(LocalDateTime dateTime, Set<Person> medicalStaffs, Integer durationInMinutes, Center center)
    {
        this.dateTime = dateTime;
        this.medicalStaffs = medicalStaffs;
        this.durationInMinutes = durationInMinutes;
        this.center = new CenterDTO(center);
    }

    public LocalDateTime getDateTime(){return dateTime;}

    public PersonDTO getBloodDonor(){return bloodDonor;}
    public Integer getDurationInMinutes(){return durationInMinutes;}

    public Integer getTermId(){return termId;}

    public void setDateTime(LocalDateTime dateTime){this.dateTime = dateTime; }

    public void setDurationInMinutes(Integer durationInMinutes){this.durationInMinutes = durationInMinutes;}
    public CenterDTO getCenter(){return center;}
    public void setCenter(CenterDTO center){this.center = center;}
}
