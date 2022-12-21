package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import app.person.model.Person;
import app.person.dto.PersonDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TermDTO {

    private Integer termId;
    private LocalDateTime dateTime;
    private Set<Person> medicalStaffs = new HashSet<>();

    private Set<PersonDTO> bloodDonors = new HashSet<>();
    private Integer durationInMinutes;

    private CenterDTO center;

    public TermDTO()
    {

    }

    public TermDTO(Term term,Center center)
    {
        this.dateTime = term.getDateTime();
        this.medicalStaffs = term.getMedicalStaffs();
        this.durationInMinutes = term.getDurationInMinutes();
        this.center = new CenterDTO(center);
    }

    public TermDTO(Term term)
    {
        this.termId = term.getTermId();
        this.dateTime = term.getDateTime();
        for (Person person: term.getBloodDonors()){
            this.bloodDonors.add(new PersonDTO(person));
        }
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

    public Set<Person> getMedicalStaffs(){return medicalStaffs;}
    public Set<PersonDTO> getBloodDonors(){return bloodDonors;}
    public Integer getDurationInMinutes(){return durationInMinutes;}

    public Integer getTermId(){return termId;}

    public void setDateTime(LocalDateTime dateTime){this.dateTime = dateTime; }
    public void setMedicalStaffs(Set<Person> medicalStaffs){this.medicalStaffs = medicalStaffs;}
    public void setDurationInMinutes(Integer durationInMinutes){this.durationInMinutes = durationInMinutes;}
    public CenterDTO getCenter(){return center;}
    public void setCenter(CenterDTO center){this.center = center;}
}
