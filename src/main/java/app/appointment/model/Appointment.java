package app.appointment.model;

import app.appointment.dto.AppointmentDTO;
import app.center.model.Term;
import app.informations.model.Informations;
import app.person.model.Person;

import javax.persistence.*;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "term_id", referencedColumnName = "termId")
    private Term term;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "personId")
    private Person person;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "informations_id", referencedColumnName = "informationsId")
    private Informations informations;

    @Column(name="started", nullable=false)
    private boolean started;

    public Appointment()
    {

    }
    public Appointment(AppointmentDTO appointmentDTO)
    {
        this.appointmentId = appointmentDTO.getAppointmentId();
        this.term = appointmentDTO.getTerm();
        this.person = appointmentDTO.getPerson();
        this.started = appointmentDTO.getStarted();
    }
    public Appointment(Integer appointmentId,Term term, Person person,boolean started)
    {
        this.appointmentId = appointmentId;
        this.term = term;
        this.person = person;
        this.started = started;
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
