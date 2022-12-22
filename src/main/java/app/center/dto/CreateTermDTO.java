package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import app.person.dto.PersonDTO;
import app.person.model.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CreateTermDTO {

    private LocalDateTime dateTime;
    private Integer medicalStaffId;
    private Integer durationInMinutes;
    private Integer managerId;

    public CreateTermDTO() {
    }

    public CreateTermDTO( LocalDateTime dateTime, int medicalStaffsId, int durationInMinutes, int managerId) {
        this.dateTime = dateTime;
        this.medicalStaffId = medicalStaffId;
        this.durationInMinutes = durationInMinutes;
        this.managerId = managerId;
    }

    public Term MapToModel(Center center, Person medicalStaff) {
        return new Term(this.dateTime,20,medicalStaff,null,center,this.durationInMinutes);
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
    public int getManagerId() { return managerId; }

    public void setManagerId(int centerId) {
        this.managerId = centerId;
    }
}
