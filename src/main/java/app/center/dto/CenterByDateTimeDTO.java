package app.center.dto;

import app.center.model.Center;
import app.center.model.Term;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;

import java.time.LocalDateTime;

public class CenterByDateTimeDTO {

    private String name;
    private String city;
    private Float avgGrade;
    private Integer termId;
    private LocalDateTime termDateTime;

    public CenterByDateTimeDTO() {
    }

    public CenterByDateTimeDTO(String name, String city, Float avgGrade, Integer termId, LocalDateTime termDateTime) {
        this.name = name;
        this.city = city;
        this.avgGrade = avgGrade;
        this.termId = termId;
        this.termDateTime = termDateTime;
    }

    public CenterByDateTimeDTO(Term term) {
        this.name = term.getCenter().getName();
        this.city = term.getCenter().getAddress().getCity();
        this.avgGrade = term.getCenter().getAvgGrade();
        this.termId = term.getTermId();
        this.termDateTime = term.getDateTime();
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

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public LocalDateTime getTermDateTime() {
        return termDateTime;
    }

    public void setTermDateTime(LocalDateTime termDateTime) {
        this.termDateTime = termDateTime;
    }
}
