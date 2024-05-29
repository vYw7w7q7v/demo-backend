package cs.vsu.event_ease.backend.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {

    @PostMapping("/sign-in")
    public void signIn() {

    }

}
