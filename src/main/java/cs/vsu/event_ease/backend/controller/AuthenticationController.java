package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.web.auth.JwtAuthenticationResponse;
import cs.vsu.event_ease.backend.web.auth.SignInRequest;
import cs.vsu.event_ease.backend.web.auth.SignUpRequest;
import cs.vsu.event_ease.backend.service.AuthenticationService;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Регистрация")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request)
            throws IncorrectDataException {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request)
            throws IncorrectDataException {
        return authenticationService.signIn(request);
    }

}
