package cs.vsu.event_ease.backend.domain;

import cs.vsu.event_ease.backend.repository.EEOpenEventRepository;
import cs.vsu.event_ease.backend.repository.EEUserRepository;

import cs.vsu.event_ease.backend.utils.ColorPrint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static cs.vsu.event_ease.backend.EEBackendTests.SUCCESS_COLOR;
import static cs.vsu.event_ease.backend.EEBackendTests.SUCCESS_DELETE_COLOR;

@SpringBootTest
public class UserEntityTests {

    @Autowired()
    private EEUserRepository userRepository;
    @Autowired()
    private EEOpenEventRepository openEventRepository;

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

    @Test
    public void userEntityAddOpenEventTest() {
        User user = new User("test@mail.ru", "test_login", "test_password", "John");
        userRepository.save(user);
        ColorPrint.println("saved user: " + user, SUCCESS_COLOR);

        OpenEvent openEvent = new OpenEvent(user, "test_event", "<<description>>",
                "место", new Date(-10800000L));
        openEventRepository.save(openEvent);
        ColorPrint.println(String.format("saved openEvent: %s", openEvent), SUCCESS_COLOR);
        user.setLogin("new_login");
        userRepository.save(user);

        System.out.println(user.getOpenEvents());

//        Optional<OpenEvent> loadedEventWithUpdatedOrganizerOptional = openEventRepository.findById(openEvent.getId());
//        Assertions.assertFalse(loadedEventWithUpdatedOrganizerOptional.isEmpty());
//        Assertions.assertEquals(loadedEventWithUpdatedOrganizerOptional.get().getOrganizer(), user);
//        ColorPrint.println("with changing user test_event.organizer also changed!", SUCCESS_COLOR);

        openEventRepository.delete(openEvent);
        Assertions.assertFalse(openEventRepository.existsById(openEvent.getId()));
        ColorPrint.println(String.format("event %s deleted!", openEvent.getName()), SUCCESS_DELETE_COLOR);

        userRepository.delete(user);
        Assertions.assertFalse(userRepository.existsById(user.getId()));
        ColorPrint.println("user " + user.getName() + " deleted", SUCCESS_DELETE_COLOR);
    }

}
