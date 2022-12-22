package app.center.dto;

import app.center.model.State;
import app.center.model.Term;
import app.person.dto.PersonDTO;

import java.time.LocalDateTime;

public class TermForPatientDTO {
    private Integer termId;
    private LocalDateTime dateTime;
    private PersonDTO bloodDonor;
    private Integer durationInMinutes;
    private State state;
    private CenterDTO center;

    public TermForPatientDTO() {
    }

    public TermForPatientDTO(Term term) {
        termId = term.getTermId();
        dateTime = term.getDateTime();
        this.bloodDonor = new PersonDTO(term.getBloodDonor());
        this.durationInMinutes = term.getDurationInMinutes();
        this.center = new CenterDTO(term.getCenter());
        this.state = term.getState();
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public PersonDTO getBloodDonor() {
        return bloodDonor;
    }

    public void setBloodDonor(PersonDTO bloodDonor) {
        this.bloodDonor = bloodDonor;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CenterDTO getCenter() {
        return center;
    }

    public void setCenter(CenterDTO center) {
        this.center = center;
    }
}
