package app.person.controller;

import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.dto.PersonDTO;
import app.person.dtos.GetPersonForProfileDTO;
import app.person.model.Person;
import app.person.service.IPersonService;
import app.user.dtos.UpdateUserDTO;
import app.user.model.User;
import app.user.service.IUserService;
import ch.qos.logback.core.net.SyslogOutputStream;
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
        Patient patient = patientService.findOne(user.getPerson().getPersonId());
        GetPersonForProfileDTO getPersonForProfileDTO = new GetPersonForProfileDTO(user,patient);
        return new ResponseEntity<GetPersonForProfileDTO>(getPersonForProfileDTO, HttpStatus.OK);
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
        user.setPassword(updateUserDTO.getPassword());
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
        List<Person> admins = personService.findAdmins();
        List<PersonDTO> personsDTO = new ArrayList<>();
        for(Person person : admins){
            personsDTO.add(new PersonDTO(person));
        }
        return new ResponseEntity<List<PersonDTO>>(personsDTO, HttpStatus.OK);
    }
}
