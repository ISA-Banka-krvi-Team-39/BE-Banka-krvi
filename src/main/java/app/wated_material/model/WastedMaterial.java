package app.wated_material.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class WastedMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;
    @Column(name="needles", nullable=false)
    private int needles;
    @Column(name="bags", nullable=false)
    private int bags;
    @Column(name="date", nullable=false)
    private LocalDateTime date;

    public WastedMaterial() {
    }

    public WastedMaterial(int needles, int bags, LocalDateTime date) {
        this.needles = needles;
        this.bags = bags;
        this.date = date;
    }

    public int getNeedles() {
        return needles;
    }

    public void setNeedles(int needles) {
        this.needles = needles;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
