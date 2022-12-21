package app.person.service;

import app.person.model.Person;
import app.person.model.PersonDescription;
import app.person.repository.IPersonDescriptionRepository;
import app.person.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDescriptionService implements IPersonDescriptionService{

    @Autowired
    private IPersonDescriptionRepository personDescriptionRepository;

    @Override
    public PersonDescription findOne(int personDescriptionId){
        return personDescriptionRepository.findOneByPersonDescriptionId(personDescriptionId);
    }
}
