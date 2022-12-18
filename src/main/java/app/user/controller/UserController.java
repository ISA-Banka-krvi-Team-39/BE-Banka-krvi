package app.user.controller;

import app.email.model.EmailDetails;
import app.email.service.IEmailService;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.model.Person;
import app.person.service.IPersonService;
import app.user.dto.CreateAdminUserDTO;
import app.user.dto.CreateUserDTO;
import app.user.model.User;
import app.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Tag(name = "User controller", description = "The User API")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IEmailService emailService;
    
    @Operation(summary = "Create new user", description = "Create new user", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserDTO.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new user when given id is not null",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Not possible to create new user data bad request",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserDTO.class)) })
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPatient(@Valid @RequestBody CreateUserDTO userDTO) throws ConstraintViolationException {
        try {
            Person person = new Person(userDTO);
            Person createdPerson = personService.create(person);
            
            User user = new User(userDTO, createdPerson);
            Patient patient = new Patient(createdPerson, 0,userDTO.getBloodType());
            
            userService.create(user);
            patientService.create(patient);
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(userDTO.getEmail());
            emailDetails.setMsgBody("Welcome to our blood bank! You can now login and schedule blood donations!");
            emailDetails.setSubject("Welcome email from blood bank team 39");
            emailService.sendWelcomeMail(emailDetails);
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @Operation(summary = "Check is Email unique", description = "Check is Email unique", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/check-email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkUid(@Parameter(name="email", description = "email to check", required = true) @PathVariable("email") String email) {
        return new ResponseEntity<Boolean>(userService.checkEmailUniqueness(email), HttpStatus.OK);
    }

    @Operation(summary = "Create new admin", description = "Create new admin", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAdminUserDTO.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new user when given id is not null",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAdminUserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Not possible to create new user data bad request",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAdminUserDTO.class)) })
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value = "/createAdmin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createAdmin(@Valid @RequestBody CreateAdminUserDTO userDTO) throws ConstraintViolationException {
        try {
            Person person = new Person(userDTO);
            Person createdPerson = personService.create(person);

            User user = new User(userDTO, createdPerson);

            userService.create(user);

        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}
