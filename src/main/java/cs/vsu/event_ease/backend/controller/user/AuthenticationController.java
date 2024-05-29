package cs.vsu.event_ease.backend.controller.user;

import cs.vsu.event_ease.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign_in")
    public ResponseEntity<String> signIn(@RequestParam(name = "name") String name,
                       @RequestParam(name = "login") String login,
                       @RequestParam(name = "email") String email,
                       @RequestParam(name = "pass") String pass) {
        if (userService.existsByLogin(login)) return new ResponseEntity<>(
                String.format("User with login = %s already exist!", login), HttpStatus.NOT_ACCEPTABLE);
        if (userService.existsByEmail(email)) return new ResponseEntity<>(
                String.format("User with email = %s already exist!", email), HttpStatus.NOT_ACCEPTABLE);
        return null;
    }

}
