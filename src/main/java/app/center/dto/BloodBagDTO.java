package app.center.dto;

import app.center.model.BloodBag;
import app.center.model.BloodType;
import app.center.model.Center;

public class BloodBagDTO {

    private BloodType bloodType;
    private Integer amount;
    private Center center;

    public BloodBagDTO()
    {

    }

    public BloodBagDTO(BloodType bloodType,Integer amount, Center center)
    {
        this.bloodType = bloodType;
        this.amount = amount;
        this.center = center;
    }
    public BloodBagDTO(BloodBag bloodBag)
    {
        this.bloodType = bloodBag.getBloodType();
        this.amount = bloodBag.getAmount();
        this.center = bloodBag.getCenter();
    }

    public BloodType getBloodType(){return bloodType;}
    public Integer getAmount(){return amount;}
}
