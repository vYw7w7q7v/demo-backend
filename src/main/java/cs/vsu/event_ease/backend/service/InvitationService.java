package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.Invitation;
import cs.vsu.event_ease.backend.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    public void save(Invitation invitation) {
        invitationRepository.save(invitation);
    }

    public boolean existsByEventIdAndEmail(UUID eventId, String email) {
        return invitationRepository.existsByEventIdAndEmail(eventId, email);
    }
}
