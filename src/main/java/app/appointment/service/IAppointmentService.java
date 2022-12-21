package app.appointment.service;

import app.appointment.model.Appointment;
import java.util.List;

public interface IAppointmentService {

    List<Appointment> findAll();
    void forbidAppointment(Appointment appointment);

    Appointment findOneByAppointmentId(Integer id);

    Appointment create(Appointment appointment);
    Appointment save(Appointment appointment);
}
