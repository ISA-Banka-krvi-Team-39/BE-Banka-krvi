package app.person.controller;

import app.person.model.Person;
import app.person.service.IPersonService;
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

import java.util.List;
import java.util.Optional;

@Tag(name = "Persons controller", description = "The Person API")
@RestController
@RequestMapping(value = "/api/person")
public class PersonController {
    @Autowired
    private IPersonService personService;
    
    @Operation(summary = "Create new person", description = "Create new person", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new person when given id is not null",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createBloodDonor(@RequestBody Person person) {
        Person createdPerson = null;
        try {
            createdPerson = personService.create(person);
        } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<Person>(createdPerson,HttpStatus.OK);
    }
    
    @Operation(summary = "Get all Persons", description = "Get all Persons", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = personService.findAll();
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    @Operation(summary = "Get one Person", description = "Get one Person", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getOne(@Parameter(name="id", description = "ID of a person to return", required = true) @PathVariable("id") int id) {
        Optional<Person> person = personService.getOne(id);
        return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
    }
}
