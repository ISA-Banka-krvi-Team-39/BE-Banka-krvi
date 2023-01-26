package app.wated_material.controller;

import app.center.dto.CreateTermWithPatientDTO;
import app.center.dto.TermsStatisticsDTO;
import app.wated_material.dto.WastedMaterialStatisticsDTO;
import app.wated_material.service.IWastedMaterialService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "WastedMaterial controller", description = "The wastedMaterial API")
@RestController
@RequestMapping(value = "/api/wastedMaterial")
public class WastedMaterialController {

    @Autowired
    IWastedMaterialService wastedMaterialService;

    @Operation(summary = "Wasted Material Statistics", description = "WM statistics", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WastedMaterialStatisticsDTO.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/wastedMaterialStats",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WastedMaterialStatisticsDTO> termsStatistics(@RequestParam(value= "localDateTime", required =true)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        WastedMaterialStatisticsDTO wastedMaterialStatisticsDTO = new WastedMaterialStatisticsDTO(
                wastedMaterialService.getWastedNeedlesMonthly(localDateTime),
                wastedMaterialService.getWastedBagsMonthly(localDateTime),
                wastedMaterialService.getWastedNeedles3Months(localDateTime),
                wastedMaterialService.getWastedBags3Months(localDateTime),
                wastedMaterialService.getWastedNeedlesYearly(localDateTime),
                wastedMaterialService.getWastedBagsYearly(localDateTime)
                );
        return new ResponseEntity<WastedMaterialStatisticsDTO>(wastedMaterialStatisticsDTO, HttpStatus.OK);
    }
}
