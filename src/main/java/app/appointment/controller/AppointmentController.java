package app.appointment.controller;

import app.appointment.dto.AppointmentPatientInfoDTO;
import app.appointment.model.Appointment;
import app.appointment.service.IAppointmentService;
import app.center.dto.CenterDTO;
import app.center.dto.CreateCenterDTO;
import app.center.model.Term;
import app.center.service.ITermService;
import app.informations.model.Informations;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.dto.GetPersonForProfileDTO;
import app.person.model.Person;
import app.person.service.IPersonService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Appointments controller", description = "The appointment API")
@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IPersonService personService;

    @Autowired
    private ITermService termService;

    @Autowired
    private IPatientService patientService;

    @Operation(summary = "Get one appointment", description = "Get one appointment", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentPatientInfoDTO> getOne(@Parameter(name="id", description = "ID of a appointment to return", required = true) @PathVariable("id") int id) {
        Appointment workingAppointment = null;
        for(Appointment appointment : appointmentService.findAll())
        {
            if(appointment.getAppointmentId() == id)
            {
                workingAppointment = appointment;
            }
        }
        Person person = personService.findOne(workingAppointment.getPerson().getPersonId());
        Term term = termService.findOne(workingAppointment.getTerm().getTermId());
        Patient patient = patientService.findOne(workingAppointment.getPerson().getPersonId());
        AppointmentPatientInfoDTO appointmentPatientInfoDTO = new AppointmentPatientInfoDTO(workingAppointment,patient.getBloodType().toString());
        return new ResponseEntity<AppointmentPatientInfoDTO>(appointmentPatientInfoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get one appointment", description = "Get one appointment", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PutMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Informations> createInformations(@RequestBody Informations informations) {
        return new ResponseEntity<Informations>(informations, HttpStatus.OK);
    }

}
