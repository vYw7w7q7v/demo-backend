package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.dto.auth.JwtAuthenticationResponse;
import cs.vsu.event_ease.backend.dto.auth.SignInRequest;
import cs.vsu.event_ease.backend.dto.auth.SignUpRequest;
import cs.vsu.event_ease.backend.service.AuthenticationService;
import cs.vsu.event_ease.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Регистрация")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            return authenticationService.signUp(request);
        } catch (RuntimeException error) {
            return new JwtAuthenticationResponse("", error.getMessage());
        }
    }

    @Operation(summary = "Авторизация")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        try {
            return authenticationService.signIn(request);
        } catch (RuntimeException error) {
            return new JwtAuthenticationResponse("", error.getMessage());
        }

    }

}
