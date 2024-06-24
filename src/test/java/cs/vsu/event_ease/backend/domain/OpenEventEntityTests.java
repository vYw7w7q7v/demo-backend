package cs.vsu.event_ease.backend.domain;

import cs.vsu.event_ease.backend.repository.OpenEventRepository;
import cs.vsu.event_ease.backend.repository.UserRepository;
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

public class OpenEventEntityTests {

    @Autowired()
    private UserRepository userRepository;
    @Autowired()
    private OpenEventRepository openEventRepository;


    public void openEventEntityCRUDTest() {

        User organizer = new User("test@mail.ru", "organizer_login", "organizer_password", "Gus");
        userRepository.save(organizer);
        ColorPrint.println(String.format("saved organizer: %s", organizer), SUCCESS_COLOR);

        // save
        OpenEvent openEvent = new OpenEvent(organizer, "test_event", "<<description>>",
                "место", new Date(-10800000L).toString());
        openEventRepository.save(openEvent);
        ColorPrint.println(String.format("saved openEvent: %s", openEvent), SUCCESS_COLOR);

        Optional<OpenEvent> foundOpenEventOptional = openEventRepository.findById(openEvent.getId());
        Assertions.assertFalse(foundOpenEventOptional.isEmpty());
        OpenEvent foundOpenEvent = foundOpenEventOptional.get();
        ColorPrint.println(String.format("openEvent date time: %s", openEvent.getDate()), AnsiColor.YELLOW);
        ColorPrint.println(String.format("found openEvent date time: %s", foundOpenEvent.getDate()), AnsiColor.YELLOW);
        Assertions.assertEquals(foundOpenEvent, openEvent);
        ColorPrint.println(String.format("found openEvent: %s", foundOpenEvent), SUCCESS_COLOR);

        // update

        openEvent.setDescription("new_desc");
        openEventRepository.save(openEvent);
        foundOpenEventOptional = openEventRepository.findById(openEvent.getId());
        Assertions.assertFalse(foundOpenEventOptional.isEmpty());

        foundOpenEvent = foundOpenEventOptional.get();
        Assertions.assertEquals(foundOpenEvent, openEvent);
        ColorPrint.println(String.format("updated openEvent: %s", openEvent), SUCCESS_COLOR);

        // delete

        openEventRepository.delete(openEvent);
        Assertions.assertFalse(openEventRepository.existsById(openEvent.getId()));
        ColorPrint.println(String.format("event %s deleted!", openEvent.getName()), SUCCESS_DELETE_COLOR);

        // organizer exists after deleting openEvent

        Assertions.assertTrue(userRepository.existsById(organizer.getId()));
        ColorPrint.println(String.format("organizer %s still exists after deleting openEvent!",
                organizer.getName()), AnsiColor.BRIGHT_BLUE);

        userRepository.delete(organizer);
        Assertions.assertFalse(userRepository.existsById(organizer.getId()));
        ColorPrint.println(String.format("organizer %s deleted!", organizer.getName()), SUCCESS_DELETE_COLOR);

    }

}