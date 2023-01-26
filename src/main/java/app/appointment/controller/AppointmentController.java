package app.appointment.controller;

import app.appointment.dto.AppDto;
import app.appointment.dto.AppointmentDTO;
import app.appointment.dto.AppointmentPatientInfoDTO;
import app.appointment.model.Appointment;
import app.appointment.service.IAppointmentService;
import app.center.dto.CenterDTO;
import app.center.dto.CreateCenterDTO;
import app.center.model.BloodBag;
import app.center.model.Equipment;
import app.center.model.State;
import app.center.model.Term;
import app.center.service.EquipmentService;
import app.center.service.IBloodBagService;
import app.center.service.IEquipmentService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private IBloodBagService bloodBagService;

    @Autowired
    private IEquipmentService equipmentService;

    @Operation(summary = "Get one appointment", description = "Get one appointment", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
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
    @GetMapping(value="/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppDto>> getAllPatientAppointments(@Parameter(name="id", description = "ID of a appointment to return", required = true) @PathVariable("id") int id) {
        List<AppDto> apps = new ArrayList<AppDto>();
        for(Appointment appointment : appointmentService.findAll())
        {
            if(appointment.getPerson().getPersonId() == id)
            {
                apps.add(new AppDto(appointment.getAppointmentId(),appointment.getPerson().getName(),appointment.getPerson().getSurname(),appointment.getTerm().getDateTime().toString()));
            }
        }
        return new ResponseEntity<List<AppDto>>(apps, HttpStatus.OK);
    }

    @Operation(summary = "Create informations", description = "Create informations", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Informations.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/info/{usedBags}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InformationsDto> createInformations(@RequestBody InformationsDto informationsDto,@PathVariable("usedBags") int usedBags) {
        Informations informations = new Informations(informationsDto);
        informationService.create(informations);
        Appointment appo = null;
        String appid = informationsDto.getAppointmentId();

        for(Appointment appointment : appointmentService.findAll())
        {
            if(appointment.getAppointmentId().toString().equals(informationsDto.getAppointmentId()))
            {
                appo = appointment;
                appointment.setInformations(informations);
                appointmentService.save(appointment);
            }
        }

        if(appo != null) {
            appo = appointmentService.findOneByAppointmentId(Integer.parseInt(informationsDto.getAppointmentId()));
            int patid = patientService.findOneByPersonId(appo.getPerson().getPersonId()).getPatientId();
            List<BloodBag> bloodBags = bloodBagService.getAll();
            for (BloodBag b : bloodBags) {
                if (b.getBloodType().toString().equals(informations.getBloodType().toString())) {
                    b.setAmount(b.getAmount() + usedBags);
                    bloodBagService.save(b);
                }
            }
        }
        return new ResponseEntity<InformationsDto>(informationsDto, HttpStatus.OK);
    }
    @Operation(summary = "Create informations", description = "Create informations", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Informations.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/{bag}/{needle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateEquipment(@PathVariable("bag") int bag,@PathVariable("needle") int needle) {
        for(Equipment e : equipmentService.getAll())
        {
            if(e.getEquipmentId() == 1)
                e.setAmount(e.getAmount() - needle);
            if(e.getEquipmentId() == 2)
                e.setAmount(e.getAmount() - bag);

            equipmentService.save(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Give penal", description = "Give penal", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Patient.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(value="/penal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> givePenal(@RequestBody AppointmentDTO appointmentDTO) {
        Patient patient = null;
        for(Patient pat : patientService.findAll())
        {
            if(pat.getPerson().getPersonId() == appointmentDTO.getPersonId())
            {
                patient = patientService.findOne(pat.getPatientId());
                patient.setPenal(patient.getPenal() + 1);
                patientService.save(patient);
            }
        }

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @Operation(summary = "Cancel appointment", description = "Cancel appointment", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Appointment.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value="/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> forbidAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        System.out.println(appointmentDTO.getAppointmentId() + " " + appointmentDTO.getPersonId() + " " + appointmentDTO.getTermId());
        Appointment appointment = null;
        appointment = appointmentService.findOneByAppointmentId(appointmentDTO.getAppointmentId());
        if(appointment == null) {
            appointment = new Appointment(appointmentDTO.getAppointmentId(), termService.findOne(appointmentDTO.getTermId()), personService.findOne(appointmentDTO.getPersonId()), true);
        appointmentService.forbidAppointment(appointment);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @Operation(summary = "Create appointment", description = "Create appointment", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Appointment.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value="/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        int patid = -1;
        Appointment appointment = null;
        Appointment appointmentWithId = null;
        if(appointmentDTO != null) {
            appointment = appointmentService.findOneByAppointmentId(appointmentDTO.getAppointmentId());
        }
        if(appointment == null) {
            appointment = new Appointment(appointmentDTO.getAppointmentId(), termService.findOne(appointmentDTO.getTermId()), personService.findOne(appointmentDTO.getPersonId()), true);
            patid = patientService.findOneByPersonId(appointment.getPerson().getPersonId()).getPatientId();
            appointmentWithId = appointmentService.create(appointment);
        }

        Term term = appointment.getTerm();
        term.setState(State.DONE);
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
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/description/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDescription> getDescription(@Parameter(name="id", description = "ID of a appointment to return", required = true) @PathVariable("id") int id) {
        PersonDescription pdesc = personDescriptionService.findOne(personService.findOne(id).getPersonDescription().getPersonDescriptionId());
        return new ResponseEntity<PersonDescription>(pdesc, HttpStatus.OK);
    }

}
