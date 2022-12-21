package app.questionnaire.controller;

import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.questionnaire.dto.AnswerDTO;
import app.questionnaire.dto.QuestionnaireDTO;
import app.questionnaire.model.Answer;
import app.questionnaire.model.Question;
import app.questionnaire.model.Questionnaire;
import app.questionnaire.service.IQuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Tag(name = "Questionnaire controller", description = "The Questionnaire API")
@RestController
@RequestMapping(value = "/api/questionnaire")
public class QuestionnaireController {
    @Autowired
    private IQuestionnaireService questionnaireService;
    @Autowired
    private IPatientService patientService;

    @Operation(summary = "Get all questions", description = "Get all questions", method="GET")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/questions",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> findAll() {
        List<Question> questions = questionnaireService.findAllQuestions();
        return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
    }

    @Operation(summary = "Save questionnaire", description = "Save questionnaire", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionnaireDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Not possible to create new questionnaire data bad request",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = QuestionnaireDTO.class)) })
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPatient( @RequestBody QuestionnaireDTO questionnaireDTO) throws ConstraintViolationException {
        try {
            Patient patient = patientService.findOne(questionnaireDTO.getPatientId());
            Questionnaire createdQuestionnaire = questionnaireService.save(new Questionnaire(patient));
            for(AnswerDTO answerDTO: questionnaireDTO.getAnswers()){
                questionnaireService.save(new Answer(createdQuestionnaire,answerDTO,questionnaireService.findOneQuestion(answerDTO.getQuestionId())));
            }
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
