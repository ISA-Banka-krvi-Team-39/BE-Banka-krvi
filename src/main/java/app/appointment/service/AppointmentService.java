package app.appointment.service;

import app.appointment.model.Appointment;
import app.appointment.repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentService implements IAppointmentService{

    @Autowired
    private IAppointmentRepository appointmentRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }
}
