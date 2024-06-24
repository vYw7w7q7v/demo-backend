package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.CloseEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CloseEventRepository extends CrudRepository<CloseEvent, UUID> {
    boolean existsByName(String name);
}
