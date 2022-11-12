package app.center.controller;

import app.center.dto.CenterDTO;
import app.center.dto.CenterWithoutPersonsDTO;
import app.center.dto.CreateCenterDTO;
import app.center.model.Center;
import app.center.service.CenterService;
import app.medical_staff.model.MedicalStaff;
import app.medical_staff.model.service.IMedicalStaffService;

import app.shared.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Tag(name = "Center controller", description = "The Center API")
@RestController
@RequestMapping(value = "/api/center")
public class CenterController {
    @Autowired
    private CenterService centerService;
    @Autowired
    private IMedicalStaffService medicalStaffService;

    private AddressService addressService;


    @Operation(summary = "Get all centers", description = "Get all centers", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pageable.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/list/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CenterWithoutPersonsDTO>> getAll(Pageable pageable) {
        Page<Center> centers = centerService.getAll(pageable);
        Page<CenterWithoutPersonsDTO> pages = centers.map(this::mapCenterToDTO);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }
    private CenterWithoutPersonsDTO mapCenterToDTO(final Center center) {
        return new CenterWithoutPersonsDTO(center);
    }
    
    @Operation(summary = "Get center by id", description = "Get center by id", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Center.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CenterDTO> findById(@PathVariable Integer id) {
        Center center = centerService.findOne(id);
        List<MedicalStaff> medicalStaffList = medicalStaffService.findAllByWorkingCenter(center);
        return new ResponseEntity<>(new CenterDTO(center,medicalStaffList), HttpStatus.OK);
    }
    @Operation(summary = "Update center", description = "Update center", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Center.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="/{id}",consumes = "application/json")
    public ResponseEntity<CenterDTO> updateCenter(@RequestBody CenterDTO centerDTO) {

        // a student must exist
        Center center = centerService.findOne(centerDTO.getCenterId());
        //Address address = addressService.findOne(centerDTO.getAddress());

        if (center == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        center.setName(centerDTO.getName());
        center.setDescription(centerDTO.getDescription());
        center.setAvgGrade(centerDTO.getAvgGrade());
        if(!center.getAddress().equals(centerDTO.getAddress()))
        {
            center.setAddress(centerDTO.getAddress());
        }
        else
        {
            //center.setAddress(address);
        }
        /*if(center.getAddress().getCity().equals(centerDTO.getAddress().getCity()) && center.getAddress().getCountry().equals(centerDTO.getAddress().getCountry()) && center.getAddress().getStreetName().equals(centerDTO.getAddress().getStreetName()) )
        {
            addressService.update(centerDTO.getAddress());
        }*/

        center = centerService.save(center);
        return new ResponseEntity<>(new CenterDTO(center), HttpStatus.OK);
    }

    @Operation(summary = "Register center", description = "Register center", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCenterDTO.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new center when given id is not null",
                    content = @Content)
    })
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerCenter(@RequestBody CreateCenterDTO centerForRegistration) {
        Center center = null;
        Center createdCenter = null;

        try{
            center = new Center(centerForRegistration);
            createdCenter = centerService.create(center);

            Set<MedicalStaff> medicalStaffs = createdCenter.getWorkingMedicalStaff();
            for (MedicalStaff ms:medicalStaffs){
                ms.setWorkingCenter(createdCenter);
                medicalStaffService.save(ms);
            }


            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
                return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }

    }

}
