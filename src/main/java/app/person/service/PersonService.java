package app.person.service;

import app.person.model.Person;
import app.person.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;
    @Override
    @Transactional
    public Page<Person> findAll(Pageable page) {
        return personRepository.findAll(page);
    }
    @Transactional
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    @Override
    @Transactional
    public Person create(Person person) { return personRepository.save(person); }
}
