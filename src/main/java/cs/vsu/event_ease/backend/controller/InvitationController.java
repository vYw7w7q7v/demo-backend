package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.service.CloseEventService;
import cs.vsu.event_ease.backend.service.InvitationService;
import cs.vsu.event_ease.backend.web.invitation.VerifyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/invitation")
@RequiredArgsConstructor
public class InvitationController {
    private final CloseEventService closeEventService;
    @PostMapping("/invite")
    public void invite(@RequestParam UUID eventId,
                       @RequestParam String email,
                       @RequestParam String design) {
        closeEventService.invite(eventId, email, design);
    }

    @GetMapping("/verify")
    public ResponseEntity<VerifyResponse> verify(@RequestParam UUID code) {
        return ResponseEntity.ok().body(closeEventService.verify(code));
    }
}
