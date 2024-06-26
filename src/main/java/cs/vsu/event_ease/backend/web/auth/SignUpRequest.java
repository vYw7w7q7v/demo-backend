package cs.vsu.event_ease.backend.web.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "John")
    @Size(min = 4, max = 50, message = "Имя пользователя должно содержать от 4 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String name;

    @Schema(description = "Логин", example = "my_login")
    @Size(min = 2, max = 50, message = "логин должен содержать от 2 до 50 символов")
    @NotBlank(message = "Логин не может быть пустыми")
    private String login;

    @Schema(description = "Адрес электронной почты", example = "johndoe@gmail.com")
    @Size(min = 4, max = 50, message = "Адрес электронной почты должен содержать от 4 до 50 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_pass123")
    @Size(min = 4, max = 50, message = "Длина пароля должна быть от 4 до 50 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
