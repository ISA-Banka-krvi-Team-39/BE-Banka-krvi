package app.person.controller;

import app.appointment.model.Appointment;
import app.appointment.service.IAppointmentService;
import app.center.model.Term;
import app.center.service.ITermService;
import app.email.service.IEmailService;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.dto.PersonDTO;
import app.person.dto.GetPersonForProfileDTO;
import app.person.model.Person;
import app.person.service.IPersonService;
import app.user.dto.UpdateUserDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Persons controller", description = "The Person API")
@RestController
@RequestMapping(value = "/api/person")
public class PersonController {
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITermService termService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAppointmentService appointmentService;
    
    @Operation(summary = "Get all Persons", description = "Get all Persons", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<Person> persons = personService.findAll();
        List<PersonDTO> personsDTO = new ArrayList<>();
        for(Person person : persons){
            personsDTO.add(new PersonDTO(person));
        }
        return new ResponseEntity<List<PersonDTO>>(personsDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get one Person", description = "Get one Person", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetPersonForProfileDTO> getOne(@Parameter(name="id", description = "ID of a person to return", required = true) @PathVariable("id") int id) {
        User user = userService.findOne(id);
        user.setPassword("");
        Person person = personService.findOne(id);
        Patient patient = patientService.findOneByPersonId(id);
        List<Appointment> appointments = appointmentService.findAll();
        GetPersonForProfileDTO getPersonForProfileDTO = new GetPersonForProfileDTO(user,patient);
        return new ResponseEntity<GetPersonForProfileDTO>(getPersonForProfileDTO, HttpStatus.OK);
    }
    @Operation(summary = "Check is Uid unique", description = "Check is Uid unique", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/check-uid/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkUid(@Parameter(name="uid", description = "uid to check", required = true) @PathVariable("uid") String uid) {
        return new ResponseEntity<Boolean>(personService.checkUidUniqueness(uid), HttpStatus.OK);
    }

    @Operation(summary = "Put User Person", description = "Put User Person", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="/{id}",consumes = "application/json")
    public HttpStatus updateUserAndPerson(@Parameter(name="id", description = "ID of a person to return", required = true) @PathVariable("id") int id, @RequestBody UpdateUserDTO updateUserDTO ) {
        User user = userService.findOne(id);
        user.updateUser(updateUserDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user);
        personService.update(user.getPerson());
        return HttpStatus.OK;
    }

    @Operation(summary = "Update password", description = "Update password", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="/landing/{id}",consumes = "application/json")
    public HttpStatus updateAdminPassword(@Parameter(name="id", description = "ID of a person to return", required = true) @PathVariable("id") int id, @RequestBody UpdateUserDTO updateUserDTO ) {
        User user = userService.findOne(id);
        user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        userService.update(user);
        personService.update(user.getPerson());
        return HttpStatus.OK;
    }

    @Operation(summary = "Get admins", description = "Get admins", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/admins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAdmins() {
        List<Person> admins = personService.findAvailableAdmins();
        List<PersonDTO> personsDTO = new ArrayList<>();
        for(Person person : admins){
            personsDTO.add(new PersonDTO(person));
        }
        return new ResponseEntity<List<PersonDTO>>(personsDTO, HttpStatus.OK);
    }
    @Operation(summary = "Get admins", description = "Get admins", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/scheduledAdmins", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findScheduledAdmins() {
        List<Person> admins = personService.findScheduledAdmins();
        List<PersonDTO> personsDTO = new ArrayList<>();
        for(Person person : admins){
            personsDTO.add(new PersonDTO(person));
        }
        return new ResponseEntity<List<PersonDTO>>(personsDTO, HttpStatus.OK);
    }
}
