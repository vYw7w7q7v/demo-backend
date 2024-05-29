package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.CloseEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EECloseEventRepository extends CrudRepository<CloseEvent, UUID> {
}
