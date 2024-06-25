package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.CloseEvent;
import cs.vsu.event_ease.backend.domain.Invitation;
import cs.vsu.event_ease.backend.repository.InvitationRepository;
import cs.vsu.event_ease.backend.service.mail.EmailService;
import cs.vsu.event_ease.backend.service.qr.QRCodeService;
import cs.vsu.event_ease.backend.web.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final EmailService emailService;
    private final QRCodeService qrCodeService;

    public void save(Invitation invitation) {
        invitationRepository.save(invitation);
    }

    public boolean existsByEventIdAndEmail(UUID eventId, String email) {
        return invitationRepository.existsByEventIdAndEmail(eventId, email);
    }

    public void sendInvitation(Invitation invitation, CloseEvent closeEvent) {
        String qrPath = String.format("src/main/resources/qr/qr_%s.png", invitation.getId());
        qrCodeService.generate(
                String.format("https://vyw7w7q7v-demo-frontend-c6cf.twc1.net/confirm-page?confirm=%s", invitation.getId()),
                qrPath
        );
        emailService.sendInvitation(invitation, closeEvent, qrPath);
    }

    public Invitation findById(UUID code) {
        return invitationRepository.findById(code)
                .orElseThrow(() -> new DataNotFoundException("Приглашение не найдено!!!"));
    }
}
