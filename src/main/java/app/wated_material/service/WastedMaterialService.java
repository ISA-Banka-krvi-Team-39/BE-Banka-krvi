package app.wated_material.service;

import app.appointment.model.Appointment;
import app.wated_material.model.WastedMaterial;
import app.wated_material.repository.IWastedMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WastedMaterialService implements IWastedMaterialService{
    @Autowired
    IWastedMaterialRepository wastedMaterialRepository;
    @Override
    public WastedMaterial create(WastedMaterial wastedMaterial) {
        return wastedMaterialRepository.save(wastedMaterial);
    }

    @Override
    public int getWastedNeedlesMonthly(LocalDateTime localDateTime) {
        int wastedMat = 0;
        for (WastedMaterial w :  wastedMaterialRepository.getWastedMaterialInBetween(localDateTime.minusMonths(1),localDateTime)){
            wastedMat += w.getNeedles();
        }
        return wastedMat;
    }

    @Override
    public int getWastedBagsMonthly(LocalDateTime localDateTime) {
        int wastedMat = 0;
        for (WastedMaterial w :  wastedMaterialRepository.getWastedMaterialInBetween(localDateTime.minusMonths(1),localDateTime)){
            wastedMat += w.getBags();
        }
        return wastedMat;
    }

    @Override
    public int getWastedNeedles3Months(LocalDateTime localDateTime) {
        int wastedMat = 0;
        for (WastedMaterial w :  wastedMaterialRepository.getWastedMaterialInBetween(localDateTime.minusMonths(3),localDateTime)){
            wastedMat += w.getNeedles();
        }
        return wastedMat;
    }

    @Override
    public int getWastedBags3Months(LocalDateTime localDateTime) {
        int wastedMat = 0;
        for (WastedMaterial w :   wastedMaterialRepository.getWastedMaterialInBetween(localDateTime.minusMonths(3),localDateTime)){
            wastedMat += w.getBags();
        }
        return wastedMat;
    }

    @Override
    public int getWastedNeedlesYearly(LocalDateTime localDateTime) {
        int wastedMat = 0;
        for (WastedMaterial w :  wastedMaterialRepository.getWastedMaterialInBetween(localDateTime.minusYears(1),localDateTime)){
            wastedMat += w.getNeedles();
        }
        return wastedMat;
    }

    @Override
    public int getWastedBagsYearly(LocalDateTime localDateTime) {
        int wastedMat = 0;
        for (WastedMaterial w :  wastedMaterialRepository.getWastedMaterialInBetween(localDateTime.minusYears(1),localDateTime)){
            wastedMat += w.getBags();
        }
        return wastedMat;
    }
}
