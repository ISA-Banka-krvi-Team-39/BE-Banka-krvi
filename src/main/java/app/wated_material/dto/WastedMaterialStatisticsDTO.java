package app.wated_material.dto;

import java.time.LocalDateTime;

public class WastedMaterialStatisticsDTO {

    private int wastedNeedlesMonthly;

    private int wastedBagsMonthly;

    private int wastedNeedles3Months;

    private int wastedBags3Months;

    private int wastedNeedlesYearly;

    private int wastedBagsYearly;

    public WastedMaterialStatisticsDTO() {
    }

    public WastedMaterialStatisticsDTO(int wastedNeedlesMonthly, int wastedBagsMonthly, int wastedNeedles3Months, int wastedBags3Months, int wastedNeedlesYearly, int wastedBagsYearly) {
        this.wastedNeedlesMonthly = wastedNeedlesMonthly;
        this.wastedBagsMonthly = wastedBagsMonthly;
        this.wastedNeedles3Months = wastedNeedles3Months;
        this.wastedBags3Months = wastedBags3Months;
        this.wastedNeedlesYearly = wastedNeedlesYearly;
        this.wastedBagsYearly = wastedBagsYearly;
    }

    public int getWastedNeedlesMonthly() {
        return wastedNeedlesMonthly;
    }

    public void setWastedNeedlesMonthly(int wastedNeedlesMonthly) {
        this.wastedNeedlesMonthly = wastedNeedlesMonthly;
    }

    public int getWastedBagsMonthly() {
        return wastedBagsMonthly;
    }

    public void setWastedBagsMonthly(int wastedBagsMonthly) {
        this.wastedBagsMonthly = wastedBagsMonthly;
    }

    public int getWastedNeedles3Months() {
        return wastedNeedles3Months;
    }

    public void setWastedNeedles3Months(int wastedNeedles3Months) {
        this.wastedNeedles3Months = wastedNeedles3Months;
    }

    public int getWastedBags3Months() {
        return wastedBags3Months;
    }

    public void setWastedBags3Months(int wastedBags3Months) {
        this.wastedBags3Months = wastedBags3Months;
    }

    public int getWastedNeedlesYearly() {
        return wastedNeedlesYearly;
    }

    public void setWastedNeedlesYearly(int wastedNeedlesYearly) {
        this.wastedNeedlesYearly = wastedNeedlesYearly;
    }

    public int getWastedBagsYearly() {
        return wastedBagsYearly;
    }

    public void setWastedBagsYearly(int wastedBagsYearly) {
        this.wastedBagsYearly = wastedBagsYearly;
    }
}
