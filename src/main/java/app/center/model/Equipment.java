package app.center.model;

import javax.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;

    @Column(name = "subject" )
    private String subject;

    @Column(name = "amount" )
    private Integer amount;

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
