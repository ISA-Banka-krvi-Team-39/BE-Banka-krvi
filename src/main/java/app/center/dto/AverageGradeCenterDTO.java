package app.center.dto;

public class AverageGradeCenterDTO {

    private int avgGrade;
    private int centerId;

    public AverageGradeCenterDTO() {
    }

    public AverageGradeCenterDTO(int avgGrade, int centerId) {
        this.avgGrade = avgGrade;
        this.centerId = centerId;
    }

    public int getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(int avgGrade) {
        this.avgGrade = avgGrade;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }
}
