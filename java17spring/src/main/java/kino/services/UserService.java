package kino.services;


import kino.exceptions.UserNotFoundException;
import kino.repositories.UserRepository;
import kino.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        //User dbuser = userRepository.save(user);
        //dbuser.setId(dbuser.getId() + 10L);
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("no user found with id: " + id));
    }

    @RolesAllowed("ADMIN")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
