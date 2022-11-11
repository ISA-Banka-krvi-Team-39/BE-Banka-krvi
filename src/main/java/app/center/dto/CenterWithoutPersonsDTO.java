package app.center.dto;

import app.center.model.Center;
import app.shared.model.Address;

public class CenterWithoutPersonsDTO {
    private String name;
    private String description;
    private Float avg_grade;
    private Address address;

    public CenterWithoutPersonsDTO() {
    }
    public CenterWithoutPersonsDTO(Center center) {
        this.name = center.getName();
        this.description = center.getDescription();
        this.address = center.getAddress();
        this.avg_grade = center.getAvgGrade();
    }
    public CenterWithoutPersonsDTO(String name, String description, Float avg_grade, Address address) {
        this.name = name;
        this.description = description;
        this.avg_grade = avg_grade;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvg_grade(Float avg_grade) {
        this.avg_grade = avg_grade;
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

    public Float getAvg_grade() {
        return avg_grade;
    }

    public Address getAddress() {
        return address;
    }
}
