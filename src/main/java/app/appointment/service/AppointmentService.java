package app.appointment.service;

import app.appointment.model.Appointment;
import app.appointment.repository.IAppointmentRepository;
import app.center.model.Center;
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
    public void forbidAppointment(Appointment appointment)
    {
        appointment.setStarted(false);
        appointmentRepository.save(appointment);
    }
    public Appointment findOneByAppointmentId(Integer id)
    {
        return appointmentRepository.findOneByAppointmentId(id);
    }
    public Appointment create(Appointment appointment)
    {
        return appointmentRepository.save(appointment);
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
