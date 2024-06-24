package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "close_event")
public class CloseEvent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "date", nullable = false)
    private String date;

    private String image;

    private String type;

    @ManyToOne
    private User organizer;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Invitation> invitations = new LinkedList<>();

    public CloseEvent(User organizer, String name, String description, String location, String date) {
        this.organizer = organizer;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    @Override
    public String toString() {
        return "CloseEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloseEvent that = (CloseEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(location, that.location) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location, date);
    }
}
