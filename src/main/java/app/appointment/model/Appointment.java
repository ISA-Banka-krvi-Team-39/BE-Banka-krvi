package app.appointment.model;

import app.appointment.dto.AppointmentDTO;
import app.center.model.Term;
import app.informations.model.Informations;
import app.patient.model.Patient;
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
    @JoinColumn(name = "informations_id", referencedColumnName = "informationsId",nullable = true)
    private Informations informations;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    private Patient patient;

    @Column(name="started", nullable=false)
    private boolean started;

    public Appointment()
    {

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
    public void setStarted(boolean started)
    {
        this.started = started;
    }
    public void setTerm(Term term)
    {
        this.term = term;
    }
    public void setPerson(Person person)
    {
        this.person = person;
    }
    public void setInformations(Informations informations)
    {
        this.informations = informations;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
