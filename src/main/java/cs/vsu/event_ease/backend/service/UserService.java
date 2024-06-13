package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.repository.UserRepository;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(username);
    }


}
