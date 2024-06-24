package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.dto.UserDto;
import cs.vsu.event_ease.backend.service.OpenEventService;
import cs.vsu.event_ease.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private OpenEventService openEventService;

    @GetMapping("/user/get")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
