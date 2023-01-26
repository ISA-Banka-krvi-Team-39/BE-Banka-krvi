package app.center.dto;

public class TermsStatisticsDTO {

    private int termsInMonth;

    private int termsIn3Months;

    private int termsInYear;

    public TermsStatisticsDTO() {
    }

    public TermsStatisticsDTO(int termsInMonth, int termsIn3Months, int termsInYear) {
        this.termsInMonth = termsInMonth;
        this.termsIn3Months = termsIn3Months;
        this.termsInYear = termsInYear;
    }

    public int getTermsInMonth() {
        return termsInMonth;
    }

    public void setTermsInMonth(int termsInMonth) {
        this.termsInMonth = termsInMonth;
    }

    public int getTermsIn3Months() {
        return termsIn3Months;
    }

    public void setTermsIn3Months(int termsIn3Months) {
        this.termsIn3Months = termsIn3Months;
    }

    public int getTermsInYear() {
        return termsInYear;
    }

    public void setTermsInYear(int termsInYear) {
        this.termsInYear = termsInYear;
    }
}
