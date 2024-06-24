package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invitation")
public class Invitation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String design;

    @Enumerated(EnumType.STRING)
    private Status status = Status.WAIT;

    @ManyToOne
    private CloseEvent event;

    @ManyToOne
    private User guest;

    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitation that = (Invitation) o;
        return Objects.equals(id, that.id) && Objects.equals(design, that.design);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, design);
    }

    public enum Status {
        WAIT,
        ACCEPT,
        DECLINED
    }

}
