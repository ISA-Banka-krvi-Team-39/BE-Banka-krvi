package app.person.service;

import app.person.model.Person;

import java.util.List;

public interface IPersonService {
    List<Person> getAll();
    boolean create(Person person);
}
