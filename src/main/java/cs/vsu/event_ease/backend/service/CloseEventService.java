package cs.vsu.event_ease.backend.service;

import cs.vsu.event_ease.backend.domain.CloseEvent;
import cs.vsu.event_ease.backend.domain.Invitation;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.CloseEventDto;
import cs.vsu.event_ease.backend.dto.InvitationDto;
import cs.vsu.event_ease.backend.dto.UserDto;
import cs.vsu.event_ease.backend.dto.mapper.CloseEventMapper;
import cs.vsu.event_ease.backend.dto.mapper.InvitationMapper;
import cs.vsu.event_ease.backend.repository.CloseEventRepository;
import cs.vsu.event_ease.backend.web.exception.DataNotFoundException;
import cs.vsu.event_ease.backend.web.exception.IncorrectDataException;
import cs.vsu.event_ease.backend.web.invitation.VerifyResponse;
import cs.vsu.event_ease.backend.web.open_event.CreateEventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CloseEventService {

    private final UserService userService;
    private final CloseEventRepository closeEventRepository;
    private final InvitationService invitationService;

    private CloseEvent findById(UUID eventId) {
        return closeEventRepository.findById(eventId)
                .orElseThrow(() -> new DataNotFoundException("Не найдено закрытое событие с ID: " + eventId));
    }

    public void create(CreateEventRequest request) {
        UUID organizerId = request.getOrganizerId();
        User organizer = userService.findById(organizerId);

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
        User organizer = userService.findById(organizerId);

        return organizer.getCloseEvents().stream()
                .map(CloseEventMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void invite(UUID eventId, String email, String design) {

        Invitation.InvitationBuilder invitationBuilder = Invitation.builder();
        CloseEvent closeEvent = findById(eventId);

        invitationBuilder.event(closeEvent)
                .status(Invitation.Status.WAIT)
                .design(design)
                .email(email);

        if (invitationService.existsByEventIdAndEmail(eventId, email)) {
            throw new IncorrectDataException(String.format(
                    "Гость %s уже приглашён на мероприятие <%s>!", email, closeEvent.getName()
            ));
        }
        User guest;

        try {
            guest = userService.findByEmail(email);
            invitationBuilder.guest(guest);
        } catch (DataNotFoundException ignored) {}

        Invitation invitation = invitationBuilder.build();
        invitationService.save(invitation);
        invitationService.sendInvitation(invitation, closeEvent);
    }


    public List<InvitationDto> getInvitations(UUID eventId) {
        CloseEvent event = findById(eventId);
        List<Invitation> invitationList = event.getInvitations();
        return invitationList == null || invitationList.isEmpty() ?
                new LinkedList<>() :
                event.getInvitations().stream()
                .map(InvitationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public VerifyResponse verify(UUID code) {
        Invitation invitation;
        try {
            invitation = invitationService.findById(code);
        } catch (DataNotFoundException exception) {
            return new VerifyResponse(false, null);
        }
        return new VerifyResponse(true, invitation.getEmail());
    }
}
