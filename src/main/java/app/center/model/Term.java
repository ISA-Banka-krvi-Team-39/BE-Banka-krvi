package app.center.model;

import app.center.dto.TermDTO;
import app.person.model.Person;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer termId;

    @Column(name = "dateTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]a")
    private LocalDateTime dateTime;
    
    @Column(name = "maximumSpace" )
    private Integer maximumSpace;
    
    @Column(name = "state" )
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id",nullable = true)
    private Person bloodDonor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalStaff")
    private Person medicalStaff;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @Version
    private Integer version;

    public Term()
    {
    }

    public Term(TermDTO termDTO, Center center)
    {
        this.dateTime = termDTO.getDateTime();
        this.maximumSpace = 1;
        this.bloodDonor = bloodDonor;
        this.center = center;
        this.durationInMinutes = termDTO.getDurationInMinutes();
    }



    public Term(LocalDateTime dateTime, Integer maximumSpace, Set<Person> medicalStaffs, Person bloodDonor, Center center, Integer durationInMinutes) {
        this.dateTime = dateTime;
        this.maximumSpace = maximumSpace;
        this.bloodDonor = bloodDonor;
        this.center = center;
        this.durationInMinutes = durationInMinutes;
    }

    public Term(LocalDateTime dateTime, Integer maximumSpace, Person medicalStaff, Person bloodDonor, Center center, Integer durationInMinutes) {
        this.dateTime = dateTime;
        this.maximumSpace = maximumSpace;
        this.bloodDonor = bloodDonor;
        this.center = center;
        this.durationInMinutes = durationInMinutes;
        this.medicalStaff = medicalStaff;
        this.state = State.FREE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Objects.equals(termId, term.termId) && Objects.equals(dateTime, term.dateTime) && Objects.equals(maximumSpace, term.maximumSpace)  && Objects.equals(bloodDonor, term.bloodDonor) && Objects.equals(center, term.center) && Objects.equals(durationInMinutes, term.durationInMinutes);
    }
    public State getState() { return state; }
    public void setState(State state) { this.state = state; }
    public void setTermId(Integer termId) {
        this.termId = termId;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setMaximumSpace(Integer maximumSpace) {
        this.maximumSpace = maximumSpace;
    }
    public void setBloodDonors(Person bloodDonor) {
        this.bloodDonor = bloodDonor;
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
    public Person getBloodDonor() {
        return bloodDonor;
    }
    public Center getCenter() {
        return center;
    }
    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
