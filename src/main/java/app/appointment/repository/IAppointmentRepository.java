package app.appointment.repository;


import app.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment findOneByAppointmentId(Integer id);
}
