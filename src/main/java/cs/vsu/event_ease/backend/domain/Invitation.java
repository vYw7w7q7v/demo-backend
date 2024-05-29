package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
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

    private String status = "wait";

    @ManyToOne
    private CloseEvent event;

    @ManyToOne
    private User guest;

    public Invitation(String design, CloseEvent event, User guest) {
        this.design = design;
        this.event = event;
        this.guest = guest;
    }

    public Invitation(String design, String status, CloseEvent event, User guest) {
        this.design = design;
        this.status = status;
        this.event = event;
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", design='" + design + '\'' +
                '}';
    }

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
}
