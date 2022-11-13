package app.center.controller;

import app.center.dto.CenterDTO;
import app.center.dto.TermDTO;
import app.center.model.Center;
import app.center.model.Term;
import app.center.service.CenterService;
import app.center.service.TermService;
import app.medical_staff.model.MedicalStaff;
import app.patient.model.Patient;
import app.person.model.Person;
import app.user.dtos.CreateUserDTO;
import app.user.dtos.UpdateUserDTO;
import app.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "Term controller", description = "The Term API")
@RestController
@RequestMapping(value = "/api/term")
public class TermController {
    @Autowired
    private TermService termService;

    @Autowired
    private CenterService centerService;



    @Operation(summary = "Put Term", description = "Put Term", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="",consumes = "application/json")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ResponseEntity<String> createTerm(@RequestBody TermDTO termDTO) throws ConstraintViolationException {
        try {
            Term retTerm = termService.create(new Term(termDTO,centerService.findOne(1)));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @Operation(summary = "Get term by id", description = "Get term by id", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> findById(@PathVariable Integer id) {
        Term term = termService.findOne(id);
        return new ResponseEntity<>(new TermDTO(term,term.getCenter()), HttpStatus.OK);
    }
}
