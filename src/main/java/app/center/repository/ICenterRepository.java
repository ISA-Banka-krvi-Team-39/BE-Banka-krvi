package app.center.repository;

import app.center.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICenterRepository extends JpaRepository<Center, Integer> {
    public Center findOneByCenterId(Integer centerId);
}