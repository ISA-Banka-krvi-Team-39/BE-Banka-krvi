package app.appointment.dto;

import app.appointment.model.Appointment;
import app.center.model.Term;
import app.person.model.Person;

import javax.persistence.*;

public class AppointmentDTO {

    private Integer appointmentId;
    private Term term;
    private Person person;
    private boolean started;

    public AppointmentDTO()
    {

    }

    public AppointmentDTO(Integer appointmentId,Term term, Person person,boolean started)
    {
        this.appointmentId = appointmentId;
        this.term = term;
        this.person = person;
        this.started = started;
    }

    public AppointmentDTO(Appointment appointment)
    {
        this.appointmentId = appointment.getAppointmentId();
        this.term = appointment.getTerm();
        this.person = appointment.getPerson();
        this.started = appointment.getStarted();
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }
    public Person getPerson()
    {
        return person;
    }
    public Term getTerm()
    {
        return term;
    }
    public boolean getStarted()
    {
        return started;
    }
}
