package cs.vsu.event_ease.backend.web.open_event;

import cs.vsu.event_ease.backend.dto.OpenEventDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateOpenEventRequest {

    private String name;

    private String description;

    private String location;

    private String date;

    private String image;

    private String type;

    private UUID organizerId;

}
