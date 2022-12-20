package app.user.repository;

import app.person.model.Person;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

    public User findOneByUserId(int id);
    public User findOneByEmail(String email);
    public User findOneByActivationCode(String activationCode);
}
