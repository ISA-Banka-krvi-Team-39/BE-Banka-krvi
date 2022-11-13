package app.questionnaire.service;

import app.person.repository.IPersonRepository;
import app.questionnaire.model.Answer;
import app.questionnaire.model.Question;
import app.questionnaire.model.Questionnaire;
import app.questionnaire.repository.IAnswerRepository;
import app.questionnaire.repository.IQuestionRepository;
import app.questionnaire.repository.IQuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService implements IQuestionnaireService {
    @Autowired
    private IQuestionRepository questionRepository;
    @Autowired
    private IQuestionnaireRepository questionnaireRepository;
    @Autowired
    private IAnswerRepository answerRepository;

    @Override
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question findOneQuestion(Integer questionId) {
        return questionRepository.findOneByQuestionId(questionId);
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }
}
