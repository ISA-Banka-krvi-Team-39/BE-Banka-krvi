package app.donated_bags.service;

import app.donated_bags.model.DonatedBags;
import app.wated_material.model.WastedMaterial;

import java.time.LocalDateTime;

public interface IDonatedBagsService {

    DonatedBags create(DonatedBags donatedBags);

    int getDonatedBagsMonthly(LocalDateTime localDateTime);

    int getDonatedBags3Months(LocalDateTime localDateTime);

    int getDonatedBagsYearly(LocalDateTime localDateTime);
}
