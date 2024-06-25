package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.domain.Invitation;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.UserDto;
import cs.vsu.event_ease.backend.dto.mapper.UserMapper;
import cs.vsu.event_ease.backend.repository.UserRepository;
import cs.vsu.event_ease.backend.service.mail.EmailService;
import cs.vsu.event_ease.backend.service.qr.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    //process.env.REACT_APP_API_URL

    @GetMapping("/hello")
    public String test(@RequestParam(name = "name", defaultValue = "John") String name) {

        return String.format("Hi, %s !!!", name);
    }

    @GetMapping("/mail")
    public void mail() {
        emailService.sendSimpleEmail("egorgolovin1@mail.ru", "123", "hi");
    }

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/qr")
    public void qr() {
        qrCodeService.generate("https://vyw7w7q7v-demo-frontend-c6cf.twc1.net/confirm-page",
                "src/main/resources/qr/qr1.png");
        //emailService.sendInvitation(Invitation.builder().build(), );
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> responseEntity() {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(new User("1", "2", "3", "4")));
    }

}
