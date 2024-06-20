package cs.vsu.event_ease.backend.controller;

import cs.vsu.event_ease.backend.dto.OpenEventDto;
import cs.vsu.event_ease.backend.service.OpenEventService;
import cs.vsu.event_ease.backend.web.open_event.CreateOpenEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("open-event")
public class OpenEventController {

    @Autowired
    private OpenEventService openEventService;

    @Operation(summary = "Создание открытого события")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid CreateOpenEventRequest request) {
        openEventService.create(request);
        return ResponseEntity.ok().body("Открытое событие успешно создано!");
    }

    @Operation(summary = "Получение всех открытых событий")
    @GetMapping("/get")
    public List<OpenEventDto> get() {
        return openEventService.findAll();
    }

}
