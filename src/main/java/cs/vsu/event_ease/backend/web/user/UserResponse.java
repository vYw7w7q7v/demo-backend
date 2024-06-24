package cs.vsu.event_ease.backend.web.user;

import cs.vsu.event_ease.backend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class UserResponse {
    private UserDto user;
    private String token;
}
