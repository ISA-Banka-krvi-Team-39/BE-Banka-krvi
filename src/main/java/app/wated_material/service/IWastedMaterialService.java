package app.wated_material.service;

import app.appointment.model.Appointment;
import app.wated_material.model.WastedMaterial;

import java.time.LocalDateTime;

public interface IWastedMaterialService {

    WastedMaterial create(WastedMaterial wastedMaterial);

    int getWastedNeedlesMonthly(LocalDateTime localDateTime);

    int getWastedBagsMonthly(LocalDateTime localDateTime);

    int getWastedNeedles3Months(LocalDateTime localDateTime);

    int getWastedBags3Months(LocalDateTime localDateTime);

    int getWastedNeedlesYearly(LocalDateTime localDateTime);

    int getWastedBagsYearly(LocalDateTime localDateTime);
}
