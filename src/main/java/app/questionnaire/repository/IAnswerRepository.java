package app.questionnaire.repository;

import app.questionnaire.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerRepository extends JpaRepository<Answer, Integer> {
}
