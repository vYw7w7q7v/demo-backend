package cs.vsu.event_ease.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "open_event")
public class OpenEvent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private void setId(UUID id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    @JoinTable(
            name = "user_open_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "open_event_id")
    )
    private User organizer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "date", nullable = false)
    private Date date;

    public OpenEvent(User organizer, String name, String location, Date date) {
        this.organizer = organizer;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public OpenEvent(User organizer, String name, String description, String location, Date date) {
        this(organizer, name, location, date);
        this.description = description;
    }

}
