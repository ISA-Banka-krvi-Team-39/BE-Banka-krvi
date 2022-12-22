package app.center.service;

import app.center.model.Center;
import app.center.model.Equipment;
import app.center.repository.ICenterRepository;
import app.center.repository.IEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {
    @Autowired
    private IEquipmentRepository equipmentRepository;

    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

}
