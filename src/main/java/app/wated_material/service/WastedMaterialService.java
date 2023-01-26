package app.wated_material.service;

import app.appointment.model.Appointment;
import app.wated_material.model.WastedMaterial;
import app.wated_material.repository.IWastedMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WastedMaterialService implements IWastedMaterialService{
    @Autowired
    IWastedMaterialRepository wastedMaterialRepository;
    @Override
    public WastedMaterial create(WastedMaterial wastedMaterial) {
        return wastedMaterialRepository.save(wastedMaterial);
    }
}
