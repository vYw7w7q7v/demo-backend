package cs.vsu.event_ease.backend.web.invitation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data@Builder@AllArgsConstructor
public class VerifyResponse {
    private boolean verified;
    private String email;
}
