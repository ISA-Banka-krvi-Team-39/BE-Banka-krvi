package app.center.model;

import app.center.dto.TermDTO;
import app.person.model.Person;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer termId;

    @Column(name = "dateTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime dateTime;
    
    @Column(name = "maximumSpace" )
    private Integer maximumSpace;
    @ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.ALL})
    @JoinTable(name = "oversees", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "term_id"))
    private Set<Person> medicalStaffs = new HashSet<Person>();

    @ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.ALL})
    @JoinTable(name = "donating", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "term_id"))
    private Set<Person> bloodDonors = new HashSet<Person>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    public Term()
    {
    }

    public Term(TermDTO termDTO,Center center)
    {
        this.dateTime = termDTO.getDateTime();
        this.maximumSpace = 20;
        this.medicalStaffs = termDTO.getMedicalStaffs();
        this.bloodDonors = bloodDonors;
        this.center = center;
        this.durationInMinutes = termDTO.getDurationInMinutes();
    }

    public Term(Integer termId, LocalDateTime dateTime, Integer maximumSpace, Set<Person> medicalStaffs, Set<Person> bloodDonors, Center center, Integer durationInMinutes) {
        this.termId = termId;
        this.dateTime = dateTime;
        this.maximumSpace = maximumSpace;
        this.medicalStaffs = medicalStaffs;
        this.bloodDonors = bloodDonors;
        this.center = center;
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Objects.equals(termId, term.termId) && Objects.equals(dateTime, term.dateTime) && Objects.equals(maximumSpace, term.maximumSpace) && Objects.equals(medicalStaffs, term.medicalStaffs) && Objects.equals(bloodDonors, term.bloodDonors) && Objects.equals(center, term.center) && Objects.equals(durationInMinutes, term.durationInMinutes);
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMaximumSpace(Integer maximumSpace) {
        this.maximumSpace = maximumSpace;
    }

    public void setMedicalStaffs(Set<Person> medicalStaffs) {
        this.medicalStaffs = medicalStaffs;
    }

    public void setBloodDonors(Set<Person> bloodDonors) {
        this.bloodDonors = bloodDonors;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getTermId() {
        return termId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getMaximumSpace() {
        return maximumSpace;
    }

    public Set<Person> getMedicalStaffs() {
        return medicalStaffs;
    }

    public Set<Person> getBloodDonors() {
        return bloodDonors;
    }

    public Center getCenter() {
        return center;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }
}
