package cs.vsu.event_ease.backend.web.open_event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class CreateOpenEventRequest {

    @Schema(description = "Название открытого события", example = "")
    @Size(min = 2, max = 50, message = "")
    @NotBlank(message = "")
    private String name;

    private String description;

    private String location;

    private Date date;

    private String picture;

}
