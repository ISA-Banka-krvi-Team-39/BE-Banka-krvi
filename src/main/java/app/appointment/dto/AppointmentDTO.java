package app.appointment.dto;

import app.appointment.model.Appointment;
import app.center.model.Term;
import app.person.model.Person;

import javax.persistence.*;

public class AppointmentDTO {

    private Integer appointmentId;
    private Integer termId;
    private Integer personId;
    private boolean started;

    public AppointmentDTO()
    {

    }

    public AppointmentDTO(Integer appointmentId,Integer termId, Integer personId,boolean started)
    {
        this.appointmentId = appointmentId;
        this.termId = termId;
        this.personId = personId;
        this.started = started;
    }

    public AppointmentDTO(Appointment appointment)
    {
        this.appointmentId = appointment.getAppointmentId();
        this.termId = appointment.getTerm().getTermId();
        this.personId = appointment.getPerson().getPersonId();
        this.started = appointment.getStarted();
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }
    public Integer getPersonId()
    {
        return personId;
    }
    public Integer getTermId()
    {
        return termId;
    }
    public boolean getStarted()
    {
        return started;
    }
}
