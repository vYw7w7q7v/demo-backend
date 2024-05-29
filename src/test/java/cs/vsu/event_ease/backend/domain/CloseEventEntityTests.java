package cs.vsu.event_ease.backend.domain;

import cs.vsu.event_ease.backend.repository.EECloseEventRepository;
import cs.vsu.event_ease.backend.repository.EEInvitationRepository;
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
public class CloseEventEntityTests {

    @Autowired()
    private EEUserRepository userRepository;
    @Autowired()
    private EECloseEventRepository closeEventRepository;

    @Autowired
    private EEInvitationRepository invitationRepository;

    @Test
    public void closeEventEntityCRUDTest() {

        User organizer = new User("test@mail.ru", "organizer_login", "organizer_password", "Gus");
        userRepository.save(organizer);
        ColorPrint.println(String.format("saved organizer: %s", organizer), SUCCESS_COLOR);

        // save
        CloseEvent closeEvent = new CloseEvent(organizer, "test_event", "<<description>>",
                "место", new Date(-10800000L));
        closeEventRepository.save(closeEvent);
        ColorPrint.println(String.format("saved event: %s", closeEvent), SUCCESS_COLOR);

        Optional<CloseEvent> foundCloseEventOptional = closeEventRepository.findById(closeEvent.getId());
        Assertions.assertFalse(foundCloseEventOptional.isEmpty());
        CloseEvent foundCloseEvent = foundCloseEventOptional.get();

        ColorPrint.println(String.format("closeEvent date time: %s", closeEvent.getDate().getTime()), AnsiColor.YELLOW);
        ColorPrint.println(String.format("found closeEvent date time: %s", closeEvent.getDate().getTime()), AnsiColor.YELLOW);
        Assertions.assertEquals(foundCloseEvent, closeEvent);

        ColorPrint.println(organizer.getCloseEvents().toString(), SUCCESS_COLOR);

        User guest = new User("test2@mail.ru", "guest", "pass", "Guest");
        userRepository.save(guest);

        Invitation invitation = new Invitation("1", closeEvent, guest);
        invitationRepository.save(invitation);

        ColorPrint.println(closeEvent.getInvitations().toString(), SUCCESS_COLOR);

        // delete

        closeEventRepository.delete(closeEvent);
        Assertions.assertFalse(closeEventRepository.existsById(closeEvent.getId()));
        ColorPrint.println(String.format("event %s deleted!", closeEvent.getName()), SUCCESS_DELETE_COLOR);

        // organizer exists after deleting openEvent

        Assertions.assertTrue(userRepository.existsById(organizer.getId()));
        ColorPrint.println(String.format("organizer %s still exists after deleting closeEvent!",
                organizer.getName()), AnsiColor.BRIGHT_BLUE);

        userRepository.delete(organizer);
        Assertions.assertFalse(userRepository.existsById(organizer.getId()));
        ColorPrint.println(String.format("organizer %s deleted!", organizer.getName()), SUCCESS_DELETE_COLOR);

    }

}