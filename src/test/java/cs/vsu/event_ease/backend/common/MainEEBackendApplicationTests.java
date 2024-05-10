package cs.vsu.event_ease.backend.common;

import cs.vsu.event_ease.backend.entity.User;
import cs.vsu.event_ease.backend.repository.EEUserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MainEEBackendApplicationTests {

    @Autowired
    private EEUserRepository userRepository;

    @Test
    public void userEntityCreateTest() {
        User user = new User("test@mail.ru", "test_login", "test_password", "John");
        userRepository.save(user);

        Optional<User> foundUserOptional = userRepository.findById(user.getUuid());

        Assertions.assertFalse(foundUserOptional.isEmpty());
        User foundUser = foundUserOptional.get();

        Assertions.assertSame(foundUser.getUuid(), user.getUuid());
        Assertions.assertEquals(foundUser.getEmail(), user.getEmail());
        Assertions.assertEquals(foundUser.getLogin(), user.getLogin());
        Assertions.assertEquals(foundUser.getPassword(), user.getPassword());

        System.out.println("Created user UUID:" + user.getUuid());

        userRepository.deleteById(user.getUuid());
    }
}
