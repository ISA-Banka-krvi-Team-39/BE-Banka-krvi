package app.user.controller;

import app.person.model.Person;
import app.person.service.IPersonService;
import app.user.dtos.CreateUserDTO;
import app.user.model.User;
import app.user.service.IUserService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User controller", description = "The User API")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPersonService personService;
    
    @Operation(summary = "Create new user", description = "Create new user", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new user when given id is not null",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO userDTO) {
        User createdUser = null;
        Person createdPerson = null;
        try {
            Person person = new Person(userDTO);
            createdPerson = personService.create(person);
            User user = new User(userDTO,createdPerson);
            createdUser = userService.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(createdUser,HttpStatus.OK);
    }
}
