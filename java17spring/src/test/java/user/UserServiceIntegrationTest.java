package user;

import static org.assertj.core.api.Assertions.assertThat;

import kino.DemoApplication;
import kino.entities.User;
import kino.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(classes = DemoApplication.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    //@Transactional
    @Transactional(propagation = Propagation.NOT_SUPPORTED) //ohne wird nach dem Test ein rollback vollzogen
    public void testCreateUser() {
        // Act: saving the user object to the database
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");

        // Save entity to the database
        userRepository.save(user);

        // Fetch user from database
        User foundUser = userRepository.findById(user.getId()).orElse(null);

        // Assert: verifying the saved user
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getName()).isEqualTo("Jane Doe");
        assertThat(foundUser.getEmail()).isEqualTo("jane.doe@example.com");

        // Optional: Logging for debugging purpose
        System.out.println(foundUser.toString());
    }
}