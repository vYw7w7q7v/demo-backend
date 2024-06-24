package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
