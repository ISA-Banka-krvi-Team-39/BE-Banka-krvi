package app.person.service;

import app.person.model.Person;
import app.person.model.PersonType;
import app.person.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;
    
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    @Override
    public Person create(Person person) { return personRepository.save(person); }

    @Override
    public Person findOne(int id){
        return personRepository.findOneByPersonId(id);
    }

    @Override

    public Person update(Person person) { return personRepository.save(person); }

    @Override
    public List<Person> findAvailableAdmins(){return personRepository.findAvailableAdmins(PersonType.MEDICALSTAFF);}
    public List<Person> findAdmins(){return personRepository.findAdmins();}

    @Override
    public List<Person> findScheduledAdmins(){return personRepository.findScheduledAdmins();}

}
