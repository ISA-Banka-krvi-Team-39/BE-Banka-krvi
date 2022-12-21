package app.appointment.controller;

import app.appointment.dto.AppointmentDTO;
import app.appointment.dto.AppointmentPatientInfoDTO;
import app.appointment.model.Appointment;
import app.appointment.service.IAppointmentService;
import app.center.dto.CenterDTO;
import app.center.dto.CreateCenterDTO;
import app.center.model.Term;
import app.center.service.ITermService;
import app.informations.dto.InformationsDto;
import app.informations.model.Informations;
import app.informations.service.IInformationService;
import app.patient.model.Patient;
import app.patient.service.IPatientService;
import app.person.dto.GetPersonForProfileDTO;
import app.person.model.Person;
import app.person.model.PersonDescription;
import app.person.service.IPersonDescriptionService;
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

    @Autowired
    private IInformationService informationService;

    @Autowired
    private IPersonDescriptionService personDescriptionService;

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

    @Operation(summary = "Create informations", description = "Create informations", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Informations.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InformationsDto> createInformations(@RequestBody InformationsDto informationsDto) {
        Informations informations = new Informations(informationsDto);
        informationService.create(informations);
        System.out.println(informationsDto.getAppointmentId() + " eo app id");
        for(Appointment appointment : appointmentService.findAll())
        {
            if(appointment.getAppointmentId().toString().equals(informationsDto.getAppointmentId()))
            {
                appointment.setInformations(informations);
                appointmentService.save(appointment);
            }
        }
        return new ResponseEntity<InformationsDto>(informationsDto, HttpStatus.OK);
    }
    @Operation(summary = "Give penal", description = "Give penal", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Patient.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/penal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> givePenal(@Parameter(name="id", description = "ID of a appointment to return", required = true) @PathVariable("id") int id) {
        Patient patient = patientService.findOne(id);
        patient.setPenal(patient.getPenal() + 1);
        patientService.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @Operation(summary = "Cancel appointment", description = "Cancel appointment", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Appointment.class))))
    })
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value="/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> forbidAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        System.out.println(appointmentDTO.getAppointmentId() + " " + appointmentDTO.getPersonId() + " " + appointmentDTO.getTermId());
        Appointment appointment = null;
        if(appointmentDTO != null)
            appointment = appointmentService.findOneByAppointmentId(appointmentDTO.getAppointmentId());
        if(appointment == null) {
            appointment = new Appointment(appointmentDTO.getAppointmentId(), termService.findOne(appointmentDTO.getTermId()), personService.findOne(appointmentDTO.getPersonId()), true);
            appointmentService.forbidAppointment(appointment);

        }
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
    @Operation(summary = "Create appointment", description = "Create appointment", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Appointment.class))))
    })
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value="/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        Appointment appointment = null;
        Appointment appointmentWithId = null;
        if(appointmentDTO != null)
            appointment = appointmentService.findOneByAppointmentId(appointmentDTO.getAppointmentId());
        if(appointment == null) {
            appointment = new Appointment(appointmentDTO.getAppointmentId(), termService.findOne(appointmentDTO.getTermId()), personService.findOne(appointmentDTO.getPersonId()), true);
            appointmentWithId = appointmentService.create(appointment);
        }
        System.out.println(appointmentWithId.getAppointmentId() + " eo id app");
        Informations info = new Informations("","","","","","","",false,"","","");
        informationService.create(info);
        AppointmentDTO apDto = new AppointmentDTO(appointmentWithId.getAppointmentId(),appointmentWithId.getTerm().getTermId(),appointmentWithId.getPerson().getPersonId(),appointmentWithId.getStarted());


        return new ResponseEntity<>(apDto, HttpStatus.OK);
    }

    @Operation(summary = "Get description", description = "Get description", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonDescription.class))))
    })
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/description", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDescription> getDescription() {
        PersonDescription pdesc = personDescriptionService.findOne(1);
        return new ResponseEntity<PersonDescription>(pdesc, HttpStatus.OK);
    }

}
