package app.user.service;

import app.user.model.Role;
import app.user.model.User;
import app.user.repository.IUserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    public User findOne(int id) { return userRepository.findOneByUserId(id);}

    @Override
    public User findOneByActivationCode(String code) {
        return userRepository.findOneByActivationCode(code);
    }
    @Override
    public boolean checkEmailUniqueness(String email) {
        return  userRepository.findOneByEmail(email)==null;
    }

    @Override
    public User findOneByPersonId(int PersonId) {
        return userRepository.findOneByPersonId(PersonId);
    }

    @Override
    @Transactional
    public User update(User user) {return userRepository.save(user);}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }
}
