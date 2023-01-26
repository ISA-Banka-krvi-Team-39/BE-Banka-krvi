package app.donated_bags.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class DonatedBags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donatedBagsId;
    @Column(name="bags", nullable=false)
    private int bagsAmount;
    @Column(name="date", nullable=false)
    private LocalDateTime date;

    public DonatedBags() {
    }

    public DonatedBags( int bagsAmount, LocalDateTime date) {
        this.bagsAmount = bagsAmount;
        this.date = date;
    }

    public Integer getDonatedBagsId() {
        return donatedBagsId;
    }

    public void setDonatedBagsId(Integer donatedBagsId) {
        this.donatedBagsId = donatedBagsId;
    }

    public int getBagsAmount() {
        return bagsAmount;
    }

    public void setBagsAmount(int bagsAmount) {
        this.bagsAmount = bagsAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
