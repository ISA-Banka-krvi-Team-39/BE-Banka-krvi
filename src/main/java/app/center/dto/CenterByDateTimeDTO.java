package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;

import java.time.LocalDateTime;

public class CenterByDateTimeDTO {

    private String name;
    private String city;
    private Float avgGrade;
    private Integer id;

    public CenterByDateTimeDTO() {
    }

    public CenterByDateTimeDTO(String name, String city, Float avgGrade, Integer id) {
        this.name = name;
        this.city = city;
        this.avgGrade = avgGrade;
        this.id = id;
    }

    public CenterByDateTimeDTO(Center center) {
        this.name = center.getName();
        this.city = center.getAddress().getCity();
        this.avgGrade = center.getAvgGrade();
        this.id = center.getCenterId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
