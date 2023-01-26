package app.center.controller;

import app.canceled_term.model.CanceledTerm;
import app.canceled_term.service.ICanceledTermService;
import app.center.dto.*;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private ICanceledTermService canceledTermService;
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

    @Operation(summary = "Post Term", description = "Post Term", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CreateTermDTO.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="/scheduleTerm",consumes = "application/json")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ResponseEntity<CreateTermForPatientDTO> updateScheduleTerm(@RequestBody CreateTermForPatientDTO createTermDTO) {
        Term term = termService.findOne(createTermDTO.getTermId());
        term.setBloodDonor(personService.findOne(createTermDTO.getPatientId()));
        termService.save(term);
       return new ResponseEntity<CreateTermForPatientDTO>(createTermDTO,HttpStatus.OK);
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
    @Operation(summary = "Get term by id", description = "Get term by id", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/date/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findDateById(@PathVariable Integer id) {
        Term term = termService.findOne(id);
        return new ResponseEntity<String>(term.getDateTime().toString().split("T")[0], HttpStatus.OK);
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
    @Operation(summary = "Get all terms", description = "Get all terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/free/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getFree(@PathVariable("id") int id) {
        List<Term> terms = termService.getAllFree();
        List<TermDTO> termsDTO = new ArrayList<>();
        for(Term term : terms) {
            if(term.getCenter().getCenterId() == id)
            if(term.getBloodDonor() == null)
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
            throw new Exception("Seems like you had blood donation in last 6 months or will donate in next 6 months so you can't donate now!");
        if(questionnaireService.findAllByPersonId(personId).size() == 0)
            throw new Exception("You didn't fill questionnaire, you cant schedule now!");
        Term term = termService.findOne(termDTO.getTermId());
        if(term.getState()!= State.FREE)
            throw new Exception("Someone scheduled this appointment refresh page!");
        termService.schedule(term,personId);
        return sendScheduledEmail(personId);
    }

    private ResponseEntity<Boolean> sendScheduledEmail(@PathVariable("id") int personId) {
        User user = userService.findOneByPersonId(personId);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setMsgBody("Your blood bank!<br/>" +
                "Your term is scheduled <br/>");
        emailDetails.setSubject("Welcome email from blood bank team 39");
        emailService.sendWelcomeMail(emailDetails);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Operation(summary = "Schedule term", description = "Get all terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class))))
    })
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/scheduleByDate/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> scheduleTermByDate(@RequestParam("termId") int termId, @PathVariable("id") int personId) throws Exception {
        if(!termService.canPatientDonate(personId))
            throw new Exception("This patient cant donate");
        if(questionnaireService.findAllByPersonId(personId).size() == 0)
            throw new Exception("This patient cant donate because there is no questionnaire");
        Term term = termService.findOne(termId);
        Person person = personService.findOne(personId);
        term.setState(State.PENDING);
        term.setBloodDonors(person);
        termService.save(term);
        return sendScheduledEmail(personId);
    }
    
    @Operation(summary = "Get all terms by patient", description = "Get all terms by patient", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/cancel/{id}/{patientId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelTerm(@PathVariable Integer id,@PathVariable Integer patientId) throws Exception {
        if(termService.canTermBeCanceled(id))
            throw new Exception("This term cant be canceled");
        Term term = termService.findOne(id);
        term.setBloodDonors(null);
        term.setState(State.FREE);
        canceledTermService.create(new CanceledTerm(patientId,id));
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
    @Operation(summary = "Get all terms by admin", description = "Get all terms admin", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/admin/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermForPatientDTO>> getAllAdminTerms(@PathVariable("id") int id) {
        List<Term> terms = termService.getAllAdminTerms(id);
        List<TermForPatientDTO> termsDTO = new ArrayList<>();
        for(Term term : terms) {
            termsDTO.add(new TermForPatientDTO(term));
        }
        System.out.println("velicina" + termsDTO.size());
        return new ResponseEntity<>(termsDTO, HttpStatus.OK);
    }
    @Operation(summary = "Get all terms by admin", description = "Get all terms admin", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/cancel/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermForPatientDTO>> cancelTerm(@PathVariable int id) {
        termService.cancelTermById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Get last patient term", description = "Get last patient term", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TermDTO.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/findLast/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getLastPatientTerm(@PathVariable int id) {
        List<Term> terms = termService.getAll();
        List<Term> termss = new ArrayList<>();
        Term lastTerm = null;
        for(Term term : terms)
        {
            if(term.getBloodDonor() != null) {
                if (term.getBloodDonor().getPersonId() == id) {
                    System.out.println("usao da dodeli Last Term");
                    lastTerm = term;
                    termss.add(term);
                }
            }
        }
        for(Term term : termss)
        {
            System.out.println("DA vidimo: " + term.getDateTime().isAfter(lastTerm.getDateTime()) + " i ovako " + term.getDateTime().compareTo(lastTerm.getDateTime()));
            if(term.getDateTime().isAfter(lastTerm.getDateTime()))
            {

                System.out.println("usao da dodeli Last Term max");
                lastTerm = term;
            }
        }
        if(lastTerm == null) {
            lastTerm = new Term();
            lastTerm.setDateTime(LocalDateTime.now());
        }
        System.out.println(lastTerm.getDateTime().toString().split("T")[0] + " LastTerm");
        return new ResponseEntity<String>(lastTerm.getDateTime().toString().split("T")[0], HttpStatus.OK);
    }
    @Operation(summary = "Get all terms by patient", description = "Get all terms patient", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/all/done/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermForPatientDTO>> getAllDone(@PathVariable("id") int id) {
        List<Term> terms = termService.getAll();
        List<TermForPatientDTO> termsDTO = new ArrayList<>();
        for (Term term : terms) {
            if (term.getCenter().getCenterId() == id)
                if (term.getState() == State.DONE)
                    termsDTO.add(new TermForPatientDTO(term));
        }
        return new ResponseEntity<>(termsDTO, HttpStatus.OK);
    }

    @Operation(summary = "Post Term With Pateint", description = "Post Term With Patient", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CreateTermWithPatientDTO.class))))
    })
    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/createTermWithPatient",consumes = "application/json")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ResponseEntity<String> createTermWithPatient(@RequestBody CreateTermWithPatientDTO createTermWithPatientDTO) {
        if (!termService.checkTerm(createTermWithPatientDTO.getDateTime(),createTermWithPatientDTO.getDurationInMinutes())) return new ResponseEntity<String>("404",HttpStatus.OK);
        termService.create(createTermWithPatientDTO.MapToModel(centerService.findOne(createTermWithPatientDTO.getCenterId()),null,personService.findOne(createTermWithPatientDTO.getPatientId())));
        return new ResponseEntity<String>("200",HttpStatus.OK);
    }
}
