package B911021.Kim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Washer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did")
    private int id;

    @Column
    private boolean available;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "washer", cascade = CascadeType.ALL)
    private List<WasherReservation> washerReservations = new ArrayList<>();

    public Washer(boolean available) {
        this.available = available;
    }
}
