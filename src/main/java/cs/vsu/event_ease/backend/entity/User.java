package cs.vsu.event_ease.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;


import java.util.UUID;

@NoArgsConstructor@Getter
@Entity
@Table(name = "USER_")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Setter
    private String email;

    @Setter
    private String login;

    @Setter
    private String password;

    @Setter
    private String name;

    public User( String email, String login, String password, String name) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.name = name;
    }

}
