package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.OpenEventDto;
import cs.vsu.event_ease.backend.dto.mapper.CloseEventMapper;
import cs.vsu.event_ease.backend.dto.mapper.OpenEventMapper;
import cs.vsu.event_ease.backend.repository.OpenEventRepository;
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
public class OpenEventService {

    @Autowired
    private UserService userService;
    @Autowired
    private OpenEventRepository openEventRepository;


    public void create(CreateEventRequest request) throws DataNotFoundException {

        UUID organizerId = request.getOrganizerId();
        User organizer = userService.findById(organizerId);

        if (openEventRepository.existsByName(request.getName()))
            throw new IncorrectDataException("Событие с таким названием уже существует!");

        OpenEvent createdOpenEvent = OpenEvent.builder()
                .name(request.getName())
                .description(request.getDescription())
                .organizer(organizer)
                .location(request.getLocation())
                .date(request.getDate())
                .image(request.getImage())
                .type(request.getType())
                .build();

        openEventRepository.save(createdOpenEvent);
    }

    public List<OpenEventDto> findAll() {
        List<OpenEventDto> res = new LinkedList<>();
        for (OpenEvent openEvent : openEventRepository.findAll()) {
            res.add(OpenEventMapper.INSTANCE.toDto(openEvent));
        }
        return res;
    }

    public List<OpenEventDto> findByOrganizerId(UUID userId) {
        User organizer = userService.findById(userId);
        List<OpenEvent> openEvents = organizer.getCreatedOpenEvents();
        return openEvents == null || openEvents.isEmpty() ?
                new LinkedList<>() :
                openEvents.stream()
                .map(OpenEventMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

//    public void select(UUID userId, UUID openEventID) {
//        if (!openEventRepository.existsById(openEventID))
//            throw new DataNotFoundException("Открытого события с таким UUID не существует");
//        User user = userService.findById(userId);
//        if (user.getSelectedOpenEvents() != null && user.getSelectedOpenEvents().contains(openEventID)) return;
//        userService.findById(userId).addSelectedEvent(openEventID);
//    }
//
//    public List<OpenEventDto> getSelectedEvents(UUID userId) {
//        return userService.findById(userId).getSelectedOpenEvents().stream()
//                .map(openEventRepository::findById)
//                .map(x -> x.orElseThrow(() -> new DataNotFoundException("Не найдено открытое событие по UUID")))
//                .map(OpenEventMapper.INSTANCE::toDto)
//                .collect(Collectors.toList());
//    }

}
