package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.Role;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.web.auth.JwtAuthenticationResponse;
import cs.vsu.event_ease.backend.web.auth.SignInRequest;
import cs.vsu.event_ease.backend.web.auth.SignUpRequest;
import cs.vsu.event_ease.backend.service.jwt.JwtService;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) throws IncorrectDataException {

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .login(request.getLogin())
                .role(Role.USER)
                .build();

        userService.create(user);

        String jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) throws IncorrectDataException{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()
            ));
        } catch (AuthenticationException exception) {
            throw new IncorrectDataException("Указаны неверные данные");
        }


        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getLogin());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
