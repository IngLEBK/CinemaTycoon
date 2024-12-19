package kino.configurations;

import kino.entities.User;
import kino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitialSetup {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void runInitial(){
        User user1 = new User();
        user1.setName("ErsterUser");
        user1.setEmail("Erster@User.de");
        userService.saveUser(user1);

        User user2 = new User();
        user2.setName("ZweiterUser");
        user2.setEmail("Zweiter@User.de");
        userService.saveUser(user2);
    }
}
