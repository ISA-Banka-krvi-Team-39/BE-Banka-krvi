package app.questionnaire.repository;

import app.questionnaire.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
}
