package app.user.service;


import app.person.model.Person;
import app.user.model.User;

public interface IUserService {
    User create(User user);
    User findOne(int id);
    User findOneByActivationCode(String code);
    boolean checkEmailUniqueness(String email);
    User findOneByPersonId(int PersonId);
    User update(User user);
}
