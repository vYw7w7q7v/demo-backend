package cs.vsu.event_ease.backend.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EEResponse {

    @Schema(description = "Сообщение ошибки")
    public String error = "";

}
