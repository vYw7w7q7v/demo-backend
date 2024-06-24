package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.OpenEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OpenEventRepository extends CrudRepository<OpenEvent, UUID> {
    boolean existsByName(String name);
}
