package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_")
public class User implements UserDetails {

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

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @OneToMany(mappedBy = "organizer", fetch = FetchType.EAGER)
    private List<OpenEvent> createdOpenEvents = new LinkedList<>();


//    @ManyToMany ()
//    @JoinTable(name = "user_open_event",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "event_id")
//    )
    private List<UUID> selectedOpenEvents = new ArrayList<>();

//    public List<UUID> getSelectedOpenEvents() {
//        return selectedOpenEvents != null ? selectedOpenEvents : new LinkedList<>();
//    }
//
//    public void addSelectedEvent(UUID uuid) {
//        if (selectedOpenEvents == null) {
//            selectedOpenEvents = new LinkedList<>();
//        }
//        selectedOpenEvents.add(uuid);
//    }

    @OneToMany(mappedBy = "organizer", fetch = FetchType.EAGER)
    private List<CloseEvent> closeEvents = new LinkedList<>();

    public void addCloseEvent(CloseEvent closeEvent) {
        closeEvents.add(closeEvent);
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
