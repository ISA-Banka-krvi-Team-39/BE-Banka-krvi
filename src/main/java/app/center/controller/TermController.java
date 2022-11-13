package app.center.controller;

import app.center.dto.TermDTO;
import app.center.model.Term;
import app.center.service.TermService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Tag(name = "Term controller", description = "The Term API")
@RestController
@RequestMapping(value = "/api/term")
public class TermController {
    @Autowired
    private TermService termService;



    @Operation(summary = "Put Term", description = "Put Term", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="",consumes = "application/json")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ResponseEntity<String> createTerm(@RequestBody TermDTO termDTO) throws ConstraintViolationException {
        try {

            Term retTerm = termService.create(new Term(termDTO));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
