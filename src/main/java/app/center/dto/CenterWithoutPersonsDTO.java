package app.center.dto;

import app.center.model.Center;
import app.shared.model.Address;

public class CenterWithoutPersonsDTO {
    private String name;
    private String description;
    private Float avgGrade;
    private Address address;

    public CenterWithoutPersonsDTO() {
    }
    public CenterWithoutPersonsDTO(Center center) {
        this.name = center.getName();
        this.description = center.getDescription();
        this.address = center.getAddress();
        this.avgGrade = center.getAvgGrade();
    }
    public CenterWithoutPersonsDTO(String name, String description, Float avgGrade, Address address) {
        this.name = name;
        this.description = description;
        this.avgGrade = avgGrade;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvgGrade(Float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getAvgGrade() {
        return avgGrade;
    }

    public Address getAddress() {
        return address;
    }
}
