package app.donated_bags.dto;

public class DonatedBagsStatisticsDTO {

    private int donatedBagsMonthly;

    private int donatedBags3Months;

    private int donatedBagsYearly;

    public DonatedBagsStatisticsDTO() {
    }

    public DonatedBagsStatisticsDTO(int donatedBagsMonthly, int donatedBags3Months, int donatedBagsYearly) {
        this.donatedBagsMonthly = donatedBagsMonthly;
        this.donatedBags3Months = donatedBags3Months;
        this.donatedBagsYearly = donatedBagsYearly;
    }

    public int getDonatedBagsMonthly() {
        return donatedBagsMonthly;
    }

    public void setDonatedBagsMonthly(int donatedBagsMonthly) {
        this.donatedBagsMonthly = donatedBagsMonthly;
    }

    public int getDonatedBags3Months() {
        return donatedBags3Months;
    }

    public void setDonatedBags3Months(int donatedBags3Months) {
        this.donatedBags3Months = donatedBags3Months;
    }

    public int getDonatedBagsYearly() {
        return donatedBagsYearly;
    }

    public void setDonatedBagsYearly(int donatedBagsYearly) {
        this.donatedBagsYearly = donatedBagsYearly;
    }
}
