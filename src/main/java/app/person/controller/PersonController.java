package app.person.controller;

import app.person.model.Person;
import app.person.service.IPersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {
    private IPersonService personService;
    
    @PostMapping()
    public ResponseEntity<Void> createBloodDonor(@RequestBody Person person) {
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
