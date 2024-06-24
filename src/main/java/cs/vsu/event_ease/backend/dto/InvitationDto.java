package cs.vsu.event_ease.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationDto {

    private UUID id;
    private String design;
    private String status = "wait";
    private String email;

}
