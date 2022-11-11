package app.patient.controller;

import app.patient.service.IPatientService;
import app.person.repository.IPersonRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Patient controller", description = "The Patient API")
@RestController
@RequestMapping(value = "/api/patient")
public class PatientController {
    @Autowired
    private IPatientService patientService;
}
