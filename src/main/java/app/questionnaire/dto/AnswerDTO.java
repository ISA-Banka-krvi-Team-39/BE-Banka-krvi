package app.questionnaire.dto;

import app.questionnaire.model.Question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AnswerDTO {
    @NotNull(message = "Every answer must be given")
    private boolean answer;
    @NotNull(message = "Password is mandatory")
    private Integer questionId;
    @NotEmpty(message = "Question is mandatory")
    private String question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return answer == answerDTO.answer && Objects.equals(questionId, answerDTO.questionId) && Objects.equals(question, answerDTO.question);
    }

    public AnswerDTO() {
    }

    public AnswerDTO(boolean answer, Integer questionId, String question) {
        this.answer = answer;
        this.questionId = questionId;
        this.question = question;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }
}
