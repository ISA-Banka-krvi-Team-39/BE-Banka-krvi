package app.center.controller;

import app.center.dto.BloodBagDTO;
import app.center.dto.CenterWithoutPersonsDTO;
import app.center.model.BloodBag;
import app.center.model.Center;
import app.center.service.BloodBagService;
import app.center.service.CenterService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Blood Bag controller", description = "The Blood Bag API")
@RestController
@RequestMapping(value = "/api/bloodbag")
public class BloodBagController {
    @Autowired
    private BloodBagService bloodBagService;


    @Operation(summary = "Get all blood bags", description = "Get all blood bags", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BloodBag.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BloodBagDTO>> getAll(@PathVariable int id) {
        List<BloodBag> bloodBags = bloodBagService.getAll();
        List<BloodBagDTO> bloodBagsDtos = new ArrayList<BloodBagDTO>();
        for(BloodBag bloodbag : bloodBags)
        {
            if(bloodbag.getCenter().getCenterId() == id)
                bloodBagsDtos.add(new BloodBagDTO(bloodbag.getBloodType(),bloodbag.getAmount(),bloodbag.getCenter()));

        }
        return new ResponseEntity<>(bloodBagsDtos, HttpStatus.OK);
    }
}
