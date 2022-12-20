package app.informations.repository;

import app.appointment.model.Appointment;
import app.informations.model.Informations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInformationRepository extends JpaRepository<Informations, Integer> {
}
