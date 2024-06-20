package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.OpenEventDto;
import cs.vsu.event_ease.backend.dto.mapper.OpenEventMapper;
import cs.vsu.event_ease.backend.repository.OpenEventRepository;
import cs.vsu.event_ease.backend.repository.UserRepository;
import cs.vsu.event_ease.backend.web.exception.DataNotFoundException;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import cs.vsu.event_ease.backend.web.open_event.CreateOpenEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class OpenEventService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OpenEventRepository openEventRepository;


    public void create(CreateOpenEventRequest request) throws DataNotFoundException{

        UUID organizerId = request.getOrganizerId();
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new DataNotFoundException("Не зарегистрирован пользователь с ID: " + organizerId));

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



}
