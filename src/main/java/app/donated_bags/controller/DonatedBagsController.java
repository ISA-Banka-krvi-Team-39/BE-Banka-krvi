package app.donated_bags.controller;

import app.donated_bags.dto.DonatedBagsStatisticsDTO;
import app.donated_bags.service.IDonatedBagsService;
import app.wated_material.dto.WastedMaterialStatisticsDTO;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "DonatedBags controller", description = "The donatedBags API")
@RestController
@RequestMapping(value = "/api/donatedBags")
public class DonatedBagsController {

    @Autowired
    IDonatedBagsService donatedBagsService;

    @Operation(summary = "Donated Bags Statistics", description = "DB statistics", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DonatedBagsStatisticsDTO.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/donatedBagsStats",produces = MediaType.APPLICATION_JSON_VALUE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ResponseEntity<DonatedBagsStatisticsDTO> termsStatistics(@RequestParam(value= "localDateTime", required =true)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        DonatedBagsStatisticsDTO donatedBagsStatisticsDTO = new DonatedBagsStatisticsDTO(
                donatedBagsService.getDonatedBagsMonthly(localDateTime),
                donatedBagsService.getDonatedBags3Months(localDateTime),
                donatedBagsService.getDonatedBagsYearly(localDateTime)
        );
        return new ResponseEntity<DonatedBagsStatisticsDTO>(donatedBagsStatisticsDTO, HttpStatus.OK);
    }
}
