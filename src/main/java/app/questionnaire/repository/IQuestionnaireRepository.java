package app.questionnaire.repository;

import app.patient.model.Patient;
import app.questionnaire.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IQuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
    @Query("select q from Questionnaire q where q.patient.person.personId = ?1")
    public Questionnaire findOneByPersonId(int personId);
}
