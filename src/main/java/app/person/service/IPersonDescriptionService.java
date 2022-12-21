package app.person.service;

import app.person.model.Person;
import app.person.model.PersonDescription;

public interface IPersonDescriptionService {
    PersonDescription findOne(int personDescriptionId);
}
