package app.person.repository;

import app.person.model.Person;
import app.person.model.PersonType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
    
    public Page<Person> findAll(Pageable pageable);


    public Person findOneByPersonId(int id);

    public List<Person> findAllByPersonType(PersonType type);


}
