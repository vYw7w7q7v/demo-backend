package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.OpenEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EEOpenEventRepository extends CrudRepository<OpenEvent, UUID> {
}
