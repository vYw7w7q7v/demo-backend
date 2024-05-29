package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.Invitation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InvitationRepository extends CrudRepository<Invitation, UUID> {
}
