package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.repository.EEUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EEUserRepository userRepository;

    //process.env.REACT_APP_API_URL

    @GetMapping("/hello")
    public String test(@RequestParam(name = "name", defaultValue = "John") String name) {

        return String.format("Hi, %s !!!", name);
    }

    @GetMapping("/user")
    public ResponseEntity<User> responseEntity() {
        return new ResponseEntity<>(
                new User("1", "2", "3", "4"),
                HttpStatusCode.valueOf(200)
        );
    }

}
