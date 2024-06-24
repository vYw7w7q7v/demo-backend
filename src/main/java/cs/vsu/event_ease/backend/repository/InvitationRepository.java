package cs.vsu.event_ease.backend.repository;

import cs.vsu.event_ease.backend.domain.Invitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, UUID> {
    boolean existsByEventIdAndEmail(UUID eventId, String email);

}
