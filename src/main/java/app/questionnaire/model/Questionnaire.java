package app.questionnaire.model;

import app.patient.model.Patient;
import app.person.model.Person;
import app.questionnaire.dto.QuestionnaireDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Questionnaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionnaireId;

    @OneToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    
    @OneToMany(mappedBy = "questionnaire", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<Answer>();

    public Questionnaire() {
    }
    public Questionnaire(Patient patient) {
        this.patient = patient;
    }

    public Questionnaire(Integer questionnaireId, Patient patient, Set<Answer> answers) {
        this.questionnaireId = questionnaireId;
        this.patient = patient;
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questionnaire that = (Questionnaire) o;
        return Objects.equals(questionnaireId, that.questionnaireId) && Objects.equals(patient, that.patient) && Objects.equals(answers, that.answers);
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }
}
