package app.questionnaire.dto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class QuestionnaireDTO {
    @NotNull(message = "PatientId is mandatory")
    private Integer patientId;
    @NotNull(message = "Answers are mandatory")
    private List<AnswerDTO> answers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionnaireDTO that = (QuestionnaireDTO) o;
        return Objects.equals(patientId, that.patientId) && Objects.equals(answers, that.answers);
    }

    public QuestionnaireDTO() {
    }

    public QuestionnaireDTO(Integer patientId, List<AnswerDTO> answers) {
        this.patientId = patientId;
        this.answers = answers;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
