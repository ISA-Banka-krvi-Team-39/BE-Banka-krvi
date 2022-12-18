package app.user.service;


import app.person.model.Person;
import app.user.model.User;

public interface IUserService {
    User create(User user);


    User findOne(int id);
    boolean checkEmailUniqueness(String email);
    User update(User user);
}
