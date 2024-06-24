package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.UserDto;
import cs.vsu.event_ease.backend.dto.mapper.UserMapper;
import cs.vsu.event_ease.backend.repository.UserRepository;
import cs.vsu.event_ease.backend.service.jwt.JwtService;
import cs.vsu.event_ease.backend.web.exception.DataNotFoundException;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import cs.vsu.event_ease.backend.web.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void create(User user) throws IncorrectDataException{
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IncorrectDataException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IncorrectDataException("Пользователь с таким email уже существует");
        }
        userRepository.save(user);
    }

    public User getByLogin(String login) throws IncorrectDataException {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new IncorrectDataException("Пользователь с указанным логином не найден"));

    }

    public UserDetailsService userDetailsService() throws IncorrectDataException {
        return this::getByLogin;
    }


    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(userLogin);
    }

    public String updateInfo(UserDto newProfileInfo) {
        User user = userRepository.findById(newProfileInfo.getId())
                .orElseThrow(() -> new DataNotFoundException("Пользователь с данным ID не найден!"));

        user.setName(newProfileInfo.getName());
        user.setProfileImage(newProfileInfo.getProfileImage());

        // изменение логина
        if (!user.getLogin().equals(newProfileInfo.getLogin())) {
            String newLogin = newProfileInfo.getLogin();
            if (userRepository.existsByLogin(newLogin)) throw new IncorrectDataException(
                    String.format("Логин %s уже занят", newLogin)
            );
            user.setLogin(newLogin);
        }

        user.setPassword(newProfileInfo.getPassword());

        // изменение мэйла
        if (!user.getEmail().equals(newProfileInfo.getEmail())) {
            String newEmail = newProfileInfo.getEmail();
            if (userRepository.existsByEmail(newEmail)) throw new IncorrectDataException(
                    String.format("Email %s уже занят", newEmail)
            );
            //user.setEmail(newProfileInfo.getEmail());
        }

        userRepository.save(user);

        return jwtService.generateToken(user); // новый jwt
    }


    public UserResponse getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Пользователь с данным ID не найден!"));
        String jwt = jwtService.generateToken(user);
        return new UserResponse(UserMapper.INSTANCE.toDto(user), jwt);
    }

    public List<UserDto> findAll() {
        List<UserDto> res = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            res.add(UserMapper.INSTANCE.toDto(user));
        }
        return res;
    }
}
