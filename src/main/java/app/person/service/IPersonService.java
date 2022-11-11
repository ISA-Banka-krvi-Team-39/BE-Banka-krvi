package app.person.service;

import app.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    List<Person> findAll();
    Person create(Person person);

    Person update(Person person);
    Person findOne(int id);
}
