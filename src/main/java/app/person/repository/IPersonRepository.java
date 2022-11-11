package app.person.repository;

import app.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
    
    public Page<Person> findAll(Pageable pageable);

    public Person findOneByPersonId(int id);
}
