package app.systemadmin.repository;


import app.systemadmin.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISystemAdminRepository extends JpaRepository<SystemAdmin, Integer> {

}
