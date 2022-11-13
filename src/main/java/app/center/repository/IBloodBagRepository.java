package app.center.repository;

import app.center.model.BloodBag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBloodBagRepository extends JpaRepository<BloodBag, Integer> {
}
