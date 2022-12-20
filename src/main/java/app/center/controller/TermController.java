package app.center.controller;

import app.center.dto.CenterWithoutPersonsDTO;
import app.center.dto.TermDTO;
import app.center.model.Center;
import app.center.model.Term;
import app.center.service.CenterService;
import app.center.service.TermService;
import app.person.dto.PersonDTO;
import app.person.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
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

    @Operation(summary = "Get all terms", description = "Get all terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getAll() {
        List<Term> terms = termService.getAll();
        List<TermDTO> termsDTO = new ArrayList<>();
        for(Term term : terms){
            termsDTO.add(new TermDTO(term, term.getCenter()));

        }
        System.out.println(termsDTO);
        return new ResponseEntity<List<TermDTO>>(termsDTO, HttpStatus.OK);
    }
}
