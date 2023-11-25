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
    private int id;

    @Column(name = "wid")
    private int number;

    @Column
    private boolean available;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "washer", cascade = CascadeType.ALL)
    private List<WasherReservation> washerReservations = new ArrayList<>();

    public Washer(int number, boolean available) {
        this.number = number;
        this.available = available;
    }

    public void updateAvailable(boolean available) {
        this.available = available;
    }

    //비지니스 로직
    public void cancel(WasherReservation washerReservation) {
        if (this.available) {
            throw new IllegalStateException("예약되지 않은 세탁기입니다.");
        }
        updateAvailable(true);
        this.washerReservations.remove(washerReservation);
    }
}
