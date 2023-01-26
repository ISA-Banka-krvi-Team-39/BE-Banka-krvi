package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import app.person.model.Person;

import java.time.LocalDateTime;

public class CreateTermWithPatientDTO {
    private LocalDateTime dateTime;
    private Integer medicalStaffId;
    private Integer durationInMinutes;
    private Integer patientId;
    private Integer centerId;

    public CreateTermWithPatientDTO() {
    }

    public CreateTermWithPatientDTO( LocalDateTime dateTime, int medicalStaffsId, int durationInMinutes, int patientId,int centerId) {
        this.dateTime = dateTime;
        this.medicalStaffId = medicalStaffId;
        this.durationInMinutes = durationInMinutes;
        this.patientId = patientId;
        this.centerId = centerId;
    }

    public Term MapToModel(Center center, Person medicalStaff,Person donor) {
        return new Term(this.dateTime,20,medicalStaff,donor,center,this.durationInMinutes);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public int getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(int medicalStaffsId) {
        this.medicalStaffId = medicalStaffsId;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
    public int getPatientId() { return patientId; }

    public void setPatientId(int centerId) {
        this.patientId = centerId;
    }

    public int getCenterId() { return centerId; }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

}
