package app.user.repository;

import app.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
