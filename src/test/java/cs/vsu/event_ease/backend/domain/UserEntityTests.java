package cs.vsu.event_ease.backend.domain;

import cs.vsu.event_ease.backend.repository.EEUserRepository;

import cs.vsu.event_ease.backend.utils.ColorPrint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static cs.vsu.event_ease.backend.EEBackendTests.SUCCESS_COLOR;
import static cs.vsu.event_ease.backend.EEBackendTests.SUCCESS_DELETE_COLOR;

@SpringBootTest
public class UserEntityTests {

    @Autowired()
    private EEUserRepository userRepository;

    @Test
    public void userEntityCRUDTest() {

        // save
        User user = new User("test@mail.ru", "test_login", "test_password", "John");
        userRepository.save(user);
        ColorPrint.println("saved user: " + user, SUCCESS_COLOR);

        Optional<User> foundUserOptional = userRepository.findById(user.getId());
        Assertions.assertFalse(foundUserOptional.isEmpty());
        User foundUser = foundUserOptional.get();
        Assertions.assertEquals(foundUser, user);
        ColorPrint.println("found user: " + foundUser, SUCCESS_COLOR);

        // update

        user.setLogin("new_login");
        userRepository.save(user);
        foundUserOptional = userRepository.findById(user.getId());
        Assertions.assertFalse(foundUserOptional.isEmpty());
        foundUser = foundUserOptional.get();
        Assertions.assertEquals(foundUser, user);
        ColorPrint.println("updated user: " + foundUser, SUCCESS_COLOR);

        // delete

        userRepository.delete(user);
        Assertions.assertFalse(userRepository.existsById(user.getId()));
        ColorPrint.println("user " + user.getName() + " deleted", SUCCESS_DELETE_COLOR);

    }

}
