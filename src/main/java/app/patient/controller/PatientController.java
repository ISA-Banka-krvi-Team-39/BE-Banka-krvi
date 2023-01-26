package app.patient.controller;

import app.appointment.dto.AppointmentPatientInfoDTO;
import app.appointment.model.Appointment;
import app.center.dto.TermDTO;
import app.center.model.Term;
import app.center.service.ITermService;
import app.patient.dto.PatientDto;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.model.Person;
import app.person.repository.IPersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Tag(name = "Patient controller", description = "The Patient API")
@RestController
@RequestMapping(value = "/api/patient")
public class PatientController {
    @Autowired
    private IPatientService patientService;
    @Autowired
    private ITermService termService;

    @Operation(summary = "Get all patient with terms", description = "Get all patients with terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/terms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PatientDto>> getOne(@PathVariable("id") int id) {
        List<PatientDto> patients = new ArrayList<PatientDto>();
        List<PatientDto> patientss = new ArrayList<PatientDto>();
        List<Patient> patientList = patientService.findAll();
        List<Term> termList = termService.getAll();
        for(Term t :termList)
        {
            for(Patient p : patientList)
            {
                if(t.getCenter().getCenterId() == id) {
                    if (t.getBloodDonor() != null) {
                        if (t.getDateTime().isAfter(LocalDateTime.now())) {
                            if (patientService.findOneByPersonId(t.getBloodDonor().getPersonId()).getPatientId() == p.getPatientId()) {
                                patients.add(new PatientDto(p.getPatientId(), p.getPerson().getPersonId(), t.getTermId(), p.getPerson().getName(), p.getPerson().getSurname()));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(patients.size() + " ");
        return new ResponseEntity<List<PatientDto>>(patients, HttpStatus.OK);
    }
    @Operation(summary = "Get all patients", description = "Get all patients", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Term.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDto>> getAll() {
        List<Patient> patients = patientService.findAll();
        List<PatientDto> patDTO = new ArrayList<>();
        for(Patient patient : patients){
            patDTO.add(new PatientDto(patient.getPatientId(),patient.getPerson().getPersonId(),patient.getPerson().getName(),patient.getPerson().getSurname()));
        }
        return new ResponseEntity<>(patDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get patients penals", description = "Get all patients with terms", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/{id}/penals", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Integer> getPatientsPenals(@PathVariable Integer id) {
        Patient patient = patientService.findOneByPersonId(id);
        Integer patientPenals = 0;
        if(patient != null)
            patientPenals = patientService.getPatientPenals(patient.getPatientId());
        else
            patientPenals = 0;
        return new ResponseEntity<Integer>(patientPenals, HttpStatus.OK);
    }
}
