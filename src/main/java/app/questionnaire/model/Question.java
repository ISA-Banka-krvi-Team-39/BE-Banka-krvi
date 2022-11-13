package app.questionnaire.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer questionId;

    @Column(name="question", unique=false, nullable=false)
    String question;

    public Question() {
    }

    public Question(Integer questionId, String question) {
        this.questionId = questionId;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(questionId, question1.questionId) && Objects.equals(question, question1.question);
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }
}
