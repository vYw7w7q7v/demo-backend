package cs.vsu.event_ease.backend.controller.user;

import cs.vsu.event_ease.backend.dto.auth.JwtAuthenticationResponse;
import cs.vsu.event_ease.backend.dto.auth.SignUpRequest;
import cs.vsu.event_ease.backend.service.AuthenticationService;
import cs.vsu.event_ease.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

}
