package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.*;


import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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

    @OneToMany(mappedBy = "organizer", fetch = FetchType.EAGER)
    private List<OpenEvent> openEvents = new LinkedList<>();

    @OneToMany(mappedBy = "organizer", fetch = FetchType.EAGER)
    private List<CloseEvent> closeEvents = new LinkedList<>();

    @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
    private List<Invitation> invitationList = new LinkedList<>();

    public User(String email, String login, String password, String name) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, password, name);
    }
}
