package app.center.service;

import app.center.model.Center;
import app.center.model.Equipment;

import java.util.List;

public interface IEquipmentService {
    public Equipment save(Equipment equipment);
    public List<Equipment> getAll();
}
