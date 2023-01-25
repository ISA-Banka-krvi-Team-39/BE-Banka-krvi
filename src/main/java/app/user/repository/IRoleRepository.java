package app.user.repository;

import app.person.model.Person;
import app.user.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
    List<Role> findAll();
}
