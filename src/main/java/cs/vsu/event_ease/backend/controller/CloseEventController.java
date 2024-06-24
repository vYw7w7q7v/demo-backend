package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.dto.CloseEventDto;
import cs.vsu.event_ease.backend.service.CloseEventService;
import cs.vsu.event_ease.backend.web.open_event.CreateEventRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("close-event")
public class CloseEventController {

    @Autowired
    private CloseEventService closeEventService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid CreateEventRequest request) {
        closeEventService.create(request);
        return ResponseEntity.ok().body("Закрытое событие успешно создано!");
    }

    @GetMapping("/get-by-organizer-id")
    public ResponseEntity<List<CloseEventDto>> get(@RequestParam @Valid UUID organizerId) {
        return ResponseEntity.ok().body(closeEventService.findByOrganizerId(organizerId));
    }
}
