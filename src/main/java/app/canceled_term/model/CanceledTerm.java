package app.canceled_term.model;

import javax.persistence.*;

@Entity
public class CanceledTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="patientId", unique=false, nullable=false)
    private Integer patientId;
    @Column(name="termId", unique=false, nullable=false)
    private Integer termId;

    public CanceledTerm(Integer patientId, Integer termId) {
        this.patientId = patientId;
        this.termId = termId;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getId() {
        return Id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getTermId() {
        return termId;
    }
}
