package app.user.service;


import app.person.model.Person;
import app.user.model.User;

public interface IUserService {
    User create(User user);


    User findOne(int id);

    User update(User user);

}
