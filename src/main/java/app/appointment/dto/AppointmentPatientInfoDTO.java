package app.appointment.dto;

import app.appointment.model.Appointment;
import app.center.model.Term;
import app.person.model.Person;

public class AppointmentPatientInfoDTO {
    private Integer appointmentId;
    private String patientName;
    private String patientSurname;
    private String bloodType;
    private boolean started;

    public AppointmentPatientInfoDTO()
    {

    }

    public AppointmentPatientInfoDTO(Integer appointmentId,String patientName, String patientSurname,String bloodType,boolean started)
    {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.bloodType = bloodType;
        this.started = started;
    }

    public AppointmentPatientInfoDTO(Appointment appointment,String bloodType)
    {
        this.appointmentId = appointment.getAppointmentId();
        this.patientName = appointment.getPerson().getName();
        this.patientSurname = appointment.getPerson().getSurname();
        this.bloodType = bloodType;
        this.started = appointment.getStarted();
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }
    public String getPatientName()
    {
        return patientName;
    }
    public String getPatientSurname()
    {
        return patientSurname;
    }
    public String getBloodType()
    {
        return bloodType;
    }
    public boolean getStarted()
    {
        return started;
    }
}
