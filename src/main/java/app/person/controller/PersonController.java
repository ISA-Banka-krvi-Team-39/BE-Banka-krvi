package app.person.controller;

import app.person.model.Person;
import app.person.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {
    @Autowired
    private IPersonService personService;
    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> createBloodDonor(@RequestBody Person person) {
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = personService.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
}
