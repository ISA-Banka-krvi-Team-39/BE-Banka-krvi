package app.informations.model;

import app.person.model.Person;

import javax.persistence.*;
@Entity
public class Informations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer informationsId;

    @Column(name="bloodType", nullable=false)
    private String bloodType;

    @Column(name="note", nullable=false)
    private String note;

//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "person_id", referencedColumnName = "personId")
//    private Person personFilled;

    @Column(name="bakar_sulfat", nullable=false)
    private String bakarSulfat;

    @Column(name="hemoglobin", nullable=false)
    private String hemoglobin;

    @Column(name="lungs", nullable=false)
    private String lungs;

    @Column(name="heart", nullable=false)
    private String heart;

    @Column(name="bagType", nullable=false)
    private String bag;

    @Column(name="accepted", nullable=false)
    private boolean accepted;

    @Column(name="reason", nullable=false)
    private String reason;

    @Column(name="start_at", nullable=false)
    private String startAt;

    @Column(name="end_at", nullable=false)
    private String endAt;

    public Informations()
    {

    }

    public Informations(Integer informationsId, String bloodType,String note, Person personFilled, String bakarSulfat,
                        String hemoglobin,String lungs, String heart, String bag,boolean accepted,String reason,
                        String startAt,String endAt)
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
}
