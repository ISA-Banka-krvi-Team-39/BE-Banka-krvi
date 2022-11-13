package app.questionnaire.service;

import app.questionnaire.model.Answer;
import app.questionnaire.model.Question;
import app.questionnaire.model.Questionnaire;

import java.util.List;

public interface IQuestionnaireService {
    public List<Question> findAllQuestions();
    public Question findOneQuestion(Integer questionId);
    public Questionnaire save(Questionnaire questionnaire);
    public Answer save(Answer answer);
}
