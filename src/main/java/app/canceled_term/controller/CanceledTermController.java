package app.canceled_term.controller;

import app.canceled_term.service.ICanceledTermService;
import app.center.dto.CenterByDateTimeDTO;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "Canceled term controller", description = "The Blood Bag API")
@RestController
@RequestMapping(value = "/api/canceled-term")
public class CanceledTermController {

    @Autowired
    private ICanceledTermService canceledTermService;

    @Operation(summary = "Get centers that has free term", description = "Get centers that has free term", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CenterByDateTimeDTO.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/{patientId}/{termId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getAllByDateTime(@PathVariable("patientId") int patientId,@PathVariable("termId") int termId) throws Exception {
        Boolean didPatientAlreadyCancel = canceledTermService.findOneByPatientAndTerm(termId,patientId);
        if(didPatientAlreadyCancel)
            throw new Exception("You already canceled this appointment so you can schedule it");
        return new ResponseEntity<Boolean>(didPatientAlreadyCancel, HttpStatus.OK);
    }
}
