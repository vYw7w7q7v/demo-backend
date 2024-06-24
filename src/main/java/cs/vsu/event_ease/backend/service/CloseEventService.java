package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.controller.CloseEventController;
import cs.vsu.event_ease.backend.domain.CloseEvent;
import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.CloseEventDto;
import cs.vsu.event_ease.backend.dto.mapper.CloseEventMapper;
import cs.vsu.event_ease.backend.repository.CloseEventRepository;
import cs.vsu.event_ease.backend.repository.UserRepository;
import cs.vsu.event_ease.backend.web.exception.DataNotFoundException;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import cs.vsu.event_ease.backend.web.open_event.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CloseEventService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloseEventRepository closeEventRepository;
    public void create(CreateEventRequest request) {
        UUID organizerId = request.getOrganizerId();
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new DataNotFoundException("Не зарегистрирован пользователь с ID: " + organizerId));

        if (closeEventRepository.existsByName(request.getName()))
            throw new IncorrectDataException("Событие с таким названием уже существует!");

        CloseEvent createdCloseEvent = CloseEvent.builder()
                .name(request.getName())
                .description(request.getDescription())
                .organizer(organizer)
                .location(request.getLocation())
                .date(request.getDate())
                .image(request.getImage())
                .type(request.getType())
                .build();

        closeEventRepository.save(createdCloseEvent);
    }

    public List<CloseEventDto> findAll() {
        List<CloseEventDto> res = new LinkedList<>();
        for (CloseEvent closeEvent : closeEventRepository.findAll()) {
            res.add(CloseEventMapper.INSTANCE.toDto(closeEvent));
        }
        return res;
    }

    public List<CloseEventDto> findByOrganizerId(UUID organizerId) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new DataNotFoundException("Не зарегистрирован пользователь с ID: " + organizerId));

        return organizer.getCloseEvents().stream()
                .map(CloseEventMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
