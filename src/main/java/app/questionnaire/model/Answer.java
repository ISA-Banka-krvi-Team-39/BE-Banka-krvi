package app.questionnaire.model;

import app.person.model.Person;
import app.questionnaire.dto.AnswerDTO;

import javax.persistence.*;

@Entity
public class Answer {
    
    @Id
    @GeneratedValue
    private Integer answerId;

    @OneToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;
    
    @Column(name = "answer", unique=false, nullable=false)
    private boolean answer;

    public Answer() {
    }
    public Answer(Questionnaire questionnaire, AnswerDTO answerDTO,Question question) {
        this.questionnaire = questionnaire;
        this.answer = answerDTO.getAnswer();
        this.question = question;
    }

    public Answer(Integer answerId, Question question, Questionnaire questionnaire, boolean answer) {
        this.answerId = answerId;
        this.question = question;
        this.questionnaire = questionnaire;
        this.answer = answer;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public boolean isAnswer() {
        return answer;
    }
}
