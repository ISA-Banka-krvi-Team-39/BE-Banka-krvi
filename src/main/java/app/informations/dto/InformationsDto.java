package app.informations.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class InformationsDto {

    private Integer informationsId;
    private String bloodType;
    private String note;

//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "person_id", referencedColumnName = "personId")
//    private Person personFilled;

    private String bakarSulfat;

    private String hemoglobin;

    private String lungs;

    private String heart;

    private String bag;

    private boolean accepted;

    private String reason;

    private String startAt;

    private String endAt;

    private String appointmentId;


    public InformationsDto()
    {

    }

    public InformationsDto(Integer informationsId, String bloodType,String note, String bakarSulfat,
                        String hemoglobin,String lungs, String heart, String bag,boolean accepted,String reason,
                        String startAt,String endAt,String appointmentId)
    {
        this.informationsId = informationsId;
        this.bloodType = bloodType;
        this.note = note;
        //this.personFilled = personFilled;
        this.bakarSulfat = bakarSulfat;
        this.hemoglobin = hemoglobin;
        this.lungs = lungs;
        this.heart = heart;
        this.bag = bag;
        this.accepted = accepted;
        this.reason = reason;
        this.startAt = startAt;
        this.endAt = endAt;
        this.appointmentId = appointmentId;
    }
    public InformationsDto(String bloodType,String note, String bakarSulfat,
                        String hemoglobin,String lungs, String heart, String bag,boolean accepted,String reason,
                        String startAt,String endAt,String appointmentId)
    {   this.bloodType = bloodType;
        this.note = note;
        //this.personFilled = personFilled;
        this.bakarSulfat = bakarSulfat;
        this.hemoglobin = hemoglobin;
        this.lungs = lungs;
        this.heart = heart;
        this.bag = bag;
        this.accepted = accepted;
        this.reason = reason;
        this.startAt = startAt;
        this.endAt = endAt;
        this.appointmentId = appointmentId;
    }

    public String getEndAt() {
        return endAt;
    }

    public String getStartAt() {
        return startAt;
    }

    public String getReason() {
        return reason;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getBag() {
        return bag;
    }

    public String getHeart() {
        return heart;
    }

    public String getLungs() {
        return lungs;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public String getBakarSulfat() {
        return bakarSulfat;
    }

//    public Person getPersonFilled() {
//        return personFilled;
//    }
//    public void setPersonFilled(Person person)
//    {
//        personFilled = person;
//    }

    public Integer getInformationsId() {
        return informationsId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getNote() {
        return note;
    }

    public String getAppointmentId() { return appointmentId; }
}
