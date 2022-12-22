package app.appointment.dto;

import app.center.model.Term;
import app.person.model.Person;

public class AppDto {
    private Integer appointmentId;
    private String name;

    private String surname;
    private String date;

    public AppDto(Integer appointmentId, String name, String surname, String date) {
        this.appointmentId = appointmentId;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
