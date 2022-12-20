package app.appointment.service;

import app.appointment.model.Appointment;
import java.util.List;

public interface IAppointmentService {

    List<Appointment> findAll();
}
