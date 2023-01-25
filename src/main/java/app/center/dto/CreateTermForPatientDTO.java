package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import app.person.model.Person;

import java.time.LocalDateTime;

public class CreateTermForPatientDTO {
    private Integer termId;
    private Integer patientId;

    public CreateTermForPatientDTO() {
    }

    public CreateTermForPatientDTO(int termId, int patientId) {
        this.termId = termId;
        this.patientId = patientId;
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
}
