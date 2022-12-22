package app.user.repository;

import app.patient.model.Patient;
import app.person.model.Person;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Integer> {

    public User findOneByUserId(int id);
    public User findOneByEmail(String email);
    public User findOneByActivationCode(String activationCode);
    @Query("select u from User u where u.person.personId = ?1")
    public User findOneByPersonId(int personId);
}
