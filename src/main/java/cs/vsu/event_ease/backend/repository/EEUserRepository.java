package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EEUserRepository extends CrudRepository<User, UUID> {
}
