package app.user.service;

import app.user.model.User;
import app.user.repository.IUserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    public User findOne(int id) { return userRepository.findOneByUserId(id);}

    @Override
    public boolean checkEmailUniqueness(String email) {
        return  userRepository.findOneByEmail(email)==null;
    }

    @Override
    @Transactional
    public User update(User user) {return userRepository.save(user);}
}
