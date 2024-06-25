package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.dto.OpenEventDto;
import cs.vsu.event_ease.backend.service.OpenEventService;
import cs.vsu.event_ease.backend.web.open_event.CreateEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("open-event")
public class OpenEventController {

    @Autowired
    private OpenEventService openEventService;

    @Operation(summary = "Создание открытого события")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid CreateEventRequest request) {
        openEventService.create(request);
        return ResponseEntity.ok().body("Открытое событие успешно создано!");
    }

    @Operation(summary = "Получение всех открытых событий")
    @GetMapping("/get")
    public ResponseEntity<List<OpenEventDto>> get() {
        return ResponseEntity.ok().body(openEventService.findAll());
    }

    @Operation(summary = "Получение всех созданных открытых событий")
    @GetMapping("/get-created")
    public ResponseEntity<List<OpenEventDto>> getCreated(@RequestParam UUID userId) {
        return ResponseEntity.ok().body(openEventService.findByOrganizerId(userId));
    }


//    @PostMapping("/select")
//    public ResponseEntity<String> select(@RequestParam UUID userId, @RequestParam UUID eventId) {
//        openEventService.select(userId, eventId);
//        return ResponseEntity.ok().body("Событие успешно выбрано!");
//    }
//
//    @GetMapping("/get-selected")
//    public ResponseEntity<List<OpenEventDto>> getSelected(@RequestParam UUID userId) {
//        List<OpenEventDto> selectedByUser = openEventService.getSelectedEvents(userId);
//        return ResponseEntity.ok().body(selectedByUser);
//    }

}
