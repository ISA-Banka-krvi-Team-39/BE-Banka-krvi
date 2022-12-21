package app.person.repository;

import app.person.model.Person;
import app.person.model.PersonDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonDescriptionRepository extends JpaRepository<PersonDescription, Integer> {
    public PersonDescription findOneByPersonDescriptionId(Integer personDescriptionId);
}
