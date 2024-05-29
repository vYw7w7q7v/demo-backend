package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.UserDto;
import cs.vsu.event_ease.backend.dto.mapper.UserMapper;
import cs.vsu.event_ease.backend.repository.UserRepository;
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
    private UserRepository userRepository;

    //process.env.REACT_APP_API_URL

    @GetMapping("/hello")
    public String test(@RequestParam(name = "name", defaultValue = "John") String name) {

        return String.format("Hi, %s !!!", name);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> responseEntity() {
        return ResponseEntity.ok(
                UserMapper.INSTANCE.toDto(new User("1", "2", "3", "4"))
                );
    }

}
