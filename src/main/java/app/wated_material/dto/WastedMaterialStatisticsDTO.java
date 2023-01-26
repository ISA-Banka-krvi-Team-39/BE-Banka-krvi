package app.wated_material.dto;

import java.time.LocalDateTime;

public class WastedMaterialStatisticsDTO {

    private int wastedNeedles;

    private int wastedBags;

    public WastedMaterialStatisticsDTO() {
    }

    public WastedMaterialStatisticsDTO(int wastedNeedles, int wastedBags) {
        this.wastedNeedles = wastedNeedles;
        this.wastedBags = wastedBags;
    }

    public int getWastedNeedles() {
        return wastedNeedles;
    }

    public void setWastedNeedles(int wastedNeedles) {
        this.wastedNeedles = wastedNeedles;
    }

    public int getWastedBags() {
        return wastedBags;
    }

    public void setWastedBags(int wastedBags) {
        this.wastedBags = wastedBags;
    }
}
