package app.questionnaire.dto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class QuestionnaireDTO {
    @NotNull(message = "PersonId is mandatory")
    private Integer personId;
    @NotNull(message = "Answers are mandatory")
    private List<AnswerDTO> answers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionnaireDTO that = (QuestionnaireDTO) o;
        return Objects.equals(personId, that.personId) && Objects.equals(answers, that.answers);
    }

    public QuestionnaireDTO() {
    }

    public QuestionnaireDTO(Integer personId, List<AnswerDTO> answers) {
        this.personId = personId;
        this.answers = answers;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public Integer getPersonId() {
        return personId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
