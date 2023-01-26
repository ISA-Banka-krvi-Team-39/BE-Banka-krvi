package app.donated_bags.service;

import app.donated_bags.model.DonatedBags;
import app.donated_bags.repository.IDonatedBagsRepository;
import app.wated_material.model.WastedMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DonatedBagsService implements IDonatedBagsService{
    @Autowired
    IDonatedBagsRepository donatedBagsRepository;
    @Override
    public DonatedBags create(DonatedBags donatedBags) {
        return donatedBagsRepository.save(donatedBags);
    }

    @Override
    public int getDonatedBagsMonthly(LocalDateTime localDateTime) {
        int donatedBags = 0;
        for(DonatedBags donatedBag : donatedBagsRepository.getDonatedBagsInBetween(localDateTime.minusMonths(1),localDateTime)){
            donatedBags += donatedBag.getBagsAmount();
        }
        return donatedBags;
    }

    @Override
    public int getDonatedBags3Months(LocalDateTime localDateTime) {
        int donatedBags = 0;
        for(DonatedBags donatedBag : donatedBagsRepository.getDonatedBagsInBetween(localDateTime.minusMonths(3),localDateTime)){
            donatedBags += donatedBag.getBagsAmount();
        }
        return donatedBags;
    }

    @Override
    public int getDonatedBagsYearly(LocalDateTime localDateTime) {
        int donatedBags = 0;
        for(DonatedBags donatedBag : donatedBagsRepository.getDonatedBagsInBetween(localDateTime.minusYears(1),localDateTime)){
            donatedBags += donatedBag.getBagsAmount();
        }
        return donatedBags;
    }
}
