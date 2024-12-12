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
        System.out.println("Service:" + user.toString());
        User dbuser = userRepository.save(user);
        dbuser.setId(dbuser.getId() + 10L);
        System.out.println("Nach db insert:" + dbuser.toString());
        return dbuser;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("no user found with id: " + id));
    }

    @RolesAllowed("ADMIN")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
