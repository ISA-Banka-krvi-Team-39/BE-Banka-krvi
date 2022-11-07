package app.person.service;

import app.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {
    Page<Person> findAll(Pageable page);
    List<Person> findAll();
    Person create(Person person);
}
