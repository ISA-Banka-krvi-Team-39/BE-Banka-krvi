package app.center.controller;

import app.center.dto.*;
import app.center.model.Center;
import app.center.model.Term;
import app.center.service.ICenterService;
import app.center.service.ITermService;
import app.medical_staff.model.MedicalStaff;
import app.medical_staff.service.IMedicalStaffService;
import app.security.auth.TokenBasedAuthentication;
import app.shared.service.IAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Tag(name = "Center controller", description = "The Center API")
@RestController
@RequestMapping(value = "/api/center")
public class CenterController {
    @Autowired
    private ICenterService centerService;
    @Autowired
    private IMedicalStaffService medicalStaffService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ITermService termService;


    @Operation(summary = "Get all centers", description = "Get all centers or search", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pageable.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/list/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CenterWithoutPersonsDTO>> getAll(Pageable pageable,@Param("name") String name,@Param("city") String city,@Param("gradeFilterFrom") String gradeFilterFrom,@Param("gradeFilterTo") String gradeFilterTo) {
        Page<Center> centers = centerService.getAll(pageable, name.toLowerCase(),city.toLowerCase(),Integer.parseInt(gradeFilterFrom),Integer.parseInt(gradeFilterTo));
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
        MedicalStaff medicalStaff = medicalStaffService.findOneByPersonId(id);
        Center center = centerService.findOne(medicalStaff.getWorkingCenter().getCenterId());
        List<MedicalStaff> medicalStaffList = medicalStaffService.findAllByWorkingCenter(center);
        return new ResponseEntity<>(new CenterDTO(center,medicalStaffList), HttpStatus.OK);
    }
    @Operation(summary = "Find center", description = "Find center", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Center.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value = "/find/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> findCenterByPerson(@PathVariable Integer id) {
        MedicalStaff medicalStaff = medicalStaffService.findCenterIdByPersonId(id);
        return new ResponseEntity<>(medicalStaff.getPerson().getPersonId(), HttpStatus.OK);
    }
    @Operation(summary = "Update center", description = "Update center", method="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Center.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="/{id}",consumes = "application/json")
    public ResponseEntity<CenterDTO> updateCenter(@RequestBody CreateCenterDTO centerDTO) {

        Center center = centerService.findOne(centerDTO.getCenterId());
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
        List<MedicalStaff> letsSee = medicalStaffService.findAllMedicalStaff(centerDTO.getCenterId());
        Center createdCenter = centerService.save(center);
        Set<MedicalStaff> medicalStaffs = createdCenter.getWorkingMedicalStaff();
        for (MedicalStaff ms:centerDTO.getWorkingMedicalStaff()){
            for (MedicalStaff mss:letsSee)
            {
                if(ms.getPerson().getPersonId() == mss.getPerson().getPersonId())
                {
                    medicalStaffService.delete(mss);
                }
            }
        }
        int i = 0;
        for (MedicalStaff ms:centerDTO.getWorkingMedicalStaff()){
            for (MedicalStaff mss:letsSee)
            {
                if(ms.getPerson().getPersonId() == mss.getPerson().getPersonId())
                {
                    i = 1;
                }
            }
            if(i == 0) {

            ms.setWorkingCenter(center);
            medicalStaffService.save(ms);
            }
        }
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
    @Operation(summary = "Get centers that has free term", description = "Get centers that has free term", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CenterByDateTimeDTO.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/listDateTime/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CenterByDateTimeDTO>> getAllByDateTime(@RequestParam(value= "localDateTime", required =true)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime localDateTime,@RequestParam(value= "duration", required =true)Integer duration) {
        List<Center> centers = centerService.getTermsByDateTime(localDateTime,duration);
        List<CenterByDateTimeDTO> centerByDateTimeDTOS = new ArrayList<>();
        for(Center center : centers){
            centerByDateTimeDTOS.add(new CenterByDateTimeDTO(center));
        }
        return new ResponseEntity<>(centerByDateTimeDTOS, HttpStatus.OK);
    }


}
