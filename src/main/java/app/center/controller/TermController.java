package app.center.controller;

import app.center.dto.CenterWithoutPersonsDTO;
import app.center.dto.CreateCenterDTO;
import app.center.dto.CreateTermDTO;
import app.center.dto.TermDTO;
import app.center.dto.TermForPatientDTO;
import app.center.model.Center;
import app.center.dto.CreateTermDTO;
import app.center.dto.TermDTO;
import app.center.model.State;
import app.center.model.Term;
import app.center.service.ICenterService;
import app.center.service.ITermService;
import app.center.service.TermService;
import app.email.model.EmailDetails;
import app.email.service.IEmailService;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.dto.PersonDTO;
import app.person.model.Person;
import app.person.service.IPersonService;
import app.questionnaire.service.IQuestionnaireService;
import app.user.model.User;
import app.user.service.IUserService;
import app.person.service.PersonService;
import app.medical_staff.service.IMedicalStaffService;
import app.person.model.Person;
import app.person.service.IPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Term controller", description = "The Term API")
@RestController
@RequestMapping(value = "/api/term")
public class TermController {
    @Autowired
    private ITermService termService;
    @Autowired
    private ICenterService centerService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IQuestionnaireService questionnaireService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private IUserService userService;

    @Autowired
    private IMedicalStaffService medicalStaffService;

    @Operation(summary = "Post Term", description = "Post Term", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CreateTermDTO.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/createTerm",consumes = "application/json")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ResponseEntity<String> createTerm(@RequestBody CreateTermDTO createTermDTO) {
            if (!termService.checkTerm(createTermDTO.getDateTime(),createTermDTO.getDurationInMinutes())) return new ResponseEntity<String>("404",HttpStatus.OK);
            termService.create(createTermDTO.MapToModel(medicalStaffService.findOneByPersonId(createTermDTO.getManagerId()).getWorkingCenter(),personService.findOne(createTermDTO.getMedicalStaffId())));
            return new ResponseEntity<String>("200",HttpStatus.OK);
    }

    @Operation(summary = "Get term by id", description = "Get term by id", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> findById(@PathVariable Integer id) {
        Term term = termService.findOne(id);
        return new ResponseEntity<>(new TermDTO(term,term.getCenter()), HttpStatus.OK);
    }

    @Operation(summary = "Get all terms", description = "Get all terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getAll() {
        List<Term> terms = termService.getAll();
        List<TermDTO> termsDTO = new ArrayList<>();
        for(Term term : terms){
            termsDTO.add(new TermDTO(term));
        }
        return new ResponseEntity<>(termsDTO, HttpStatus.OK);
    }
    @Operation(summary = "Get all terms", description = "Get all terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/all/free",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getAllFree() {
        List<Term> terms = termService.getAllFree();
        List<TermDTO> termsDTO = new ArrayList<>();
        for(Term term : terms) {
            termsDTO.add(new TermDTO(term));
        }
        return new ResponseEntity<>(termsDTO, HttpStatus.OK);
    }

    @Operation(summary = "Schedule term", description = "Get all terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/schedule/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> scheduleTerm(@RequestBody TermDTO termDTO, @PathVariable("id") int personId) throws Exception {
        if(!termService.canPatientDonate(personId))
            throw new Exception("This patient cant donate");
        if(questionnaireService.findAllByPersonId(personId).size() == 0)
            throw new Exception("This patient cant donate because there is no questionnaire");
        Term term = termService.findOne(termDTO.getTermId());
        Person person = personService.findOne(personId);
        term.setState(State.PENDING);
        term.setBloodDonors(person);
        termService.save(term);
        User user = userService.findOneByPersonId(personId);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setMsgBody("Your blood bank!<br/>" +
                "Your term is scheduled <br/>");
        emailDetails.setSubject("Welcome email from blood bank team 39");
        emailService.sendWelcomeMail(emailDetails);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
    @Operation(summary = "Get all terms by patient", description = "Get all terms by patient", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/cancel/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelTerm(@PathVariable Integer id) throws Exception {
        if(termService.canTermBeCanceled(id))
            throw new Exception("This term cant be canceled");
        Term term = termService.findOne(id);
        term.setBloodDonors(null);
        term.setState(State.FREE);
        termService.save(term);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @Operation(summary = "Get all terms by patient", description = "Get all terms patient", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/all/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermForPatientDTO>> getAllPatientsTerms(@PathVariable Integer id) {
        List<Term> terms = termService.getAllPatientsTerms(id);
        List<TermForPatientDTO> termsDTO = new ArrayList<>();
        for(Term term : terms) {
            termsDTO.add(new TermForPatientDTO(term));
        }
        return new ResponseEntity<>(termsDTO, HttpStatus.OK);
    }
}
