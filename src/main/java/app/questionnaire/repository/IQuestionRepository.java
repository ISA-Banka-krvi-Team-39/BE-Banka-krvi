package app.questionnaire.repository;

import app.center.model.Center;
import app.questionnaire.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Integer> {
    public Question findOneByQuestionId(Integer questionId);
}
