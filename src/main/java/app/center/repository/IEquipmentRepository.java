package app.center.repository;

import app.center.model.Center;
import app.center.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipmentRepository extends JpaRepository<Equipment, Integer> {
}
