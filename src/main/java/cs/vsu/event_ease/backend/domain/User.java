package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@NoArgsConstructor
@Data@Entity
@Table(name = "user_")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    public User(String email, String login, String password, String name) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.name = name;
    }


}
