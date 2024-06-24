package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.dto.UserDto;
import cs.vsu.event_ease.backend.service.UserService;
import cs.vsu.event_ease.backend.web.auth.JwtResponse;
import cs.vsu.event_ease.backend.web.user.UserResponse;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update/name")
    public ResponseEntity<JwtResponse> updateName(@RequestParam @Valid UUID userId,
                                                  @RequestParam @Valid String name) {
        String newJwt = userService.updateName(userId, name);
        return ResponseEntity.ok().body(JwtResponse.builder().token(newJwt).build());
    }

    @PutMapping("/update/profileImage")
    public ResponseEntity<JwtResponse> updateProfileImage(@RequestParam @Valid UUID userId,
                                                          @RequestParam @Valid String profileImage) {
        String newJwt = userService.updateProfileImage(userId, profileImage);
        return ResponseEntity.ok().body(JwtResponse.builder().token(newJwt).build());
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponse> get(@RequestParam @Valid UUID id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

}
