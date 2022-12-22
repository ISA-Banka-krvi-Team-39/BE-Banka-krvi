package app.center.model;

import app.center.dto.BloodBagDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BloodBag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bloodId;

    @Column(name = "bloodType" )
    private BloodType bloodType;

    @Column(name = "amount" )
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    public BloodBag()
    {

    }

    public BloodBag(BloodType bloodType, Integer amount, Center center)
    {
        this.bloodType = bloodType;
        this.amount = amount;
        this.center = center;
    }

    public BloodBag(BloodBagDTO bloodBagDTO)
    {
        this.bloodType = bloodBagDTO.getBloodType();
        this.amount = bloodBagDTO.getAmount();

    }

    public BloodType getBloodType(){return bloodType;}
    public Integer getAmount(){return amount;}
    public Center getCenter(){return center;}

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

}
