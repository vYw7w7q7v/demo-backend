package cs.vsu.event_ease.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenEventDto {

    private UUID id;

    private String name;

    private String description;

    private String location;

    private String date;

    private String image;

    private String type;
}
