package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.List;
import java.util.UUID;

@Repository
public interface EEUserRepository extends CrudRepository<User, UUID> {

}
