package cs.vsu.event_ease.backend.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {

    @Schema(description = "Логин", example = "my_login")
    @Size(min = 5, max = 50, message = "логин должен содержать от 5 до 50 символов")
    @NotBlank(message = "Логин не может быть пустыми")
    private String login;

    @Schema(description = "Пароль", example = "my_pass123")
    @Size(min = 8, max = 50, message = "Длина пароля должна быть от 8 до 50 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
