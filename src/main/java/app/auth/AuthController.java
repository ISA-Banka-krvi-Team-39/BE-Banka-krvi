package app.auth;

import app.auth.dto.LoginUserDTO;
import app.auth.dto.TokenDTO;
import app.email.model.EmailDetails;
import app.email.service.IEmailService;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.model.Person;
import app.person.service.IPersonService;
import app.user.dto.CreateUserDTO;
import app.user.model.Role;
import app.user.model.User;
import app.user.service.IRoleService;
import app.user.service.RoleService;
import app.user.service.UserService;
import app.util.TokenUtils;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.Console;
import java.io.UnsupportedEncodingException;

@Tag(name = "Auth controller", description = "The Auth API")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleService roleService;

    @Operation(summary = "Login user", description = "Login user", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Logged in",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginUserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Loggin failed",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginUserDTO.class)) })
    })
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    public ResponseEntity<TokenDTO> createAuthenticationToken(
            @RequestBody LoginUserDTO loginUserDTO, HttpServletResponse response) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDTO.getUsername(), loginUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        if(!user.isEnabled())
            throw new Exception("Account not activated!");
        String jwt = tokenUtils.generateToken(user.getUsername(),user.getUserId(),user.getRoles());
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new TokenDTO(jwt, expiresIn));
    }
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
            Role role = roleService.findByName("ROLE_USER").get(0);
            User user = new User(userDTO, createdPerson,role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Patient patient = new Patient(createdPerson, 0,userDTO.getBloodType());

            userService.create(user);
            patientService.create(patient);
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(userDTO.getEmail());
            emailDetails.setMsgBody("Welcome to our blood bank!<br/>" +
                    "You can <a href=\"http://localhost:3000/auth/activation/"+ user.getActivationCode() +"\">Activate your account here!<a/></h2> <br/>");
            emailDetails.setSubject("Welcome email from blood bank team 39");
            emailService.sendWelcomeMail(emailDetails);
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
