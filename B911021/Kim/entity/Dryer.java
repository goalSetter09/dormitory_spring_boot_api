package B911021.Kim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Dryer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "did")
    private int number;

    @Column
    private boolean available;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "dryer", cascade = CascadeType.ALL)
    private List<DryerReservation> dryerReservations = new ArrayList<>();


    public Dryer(int number, boolean available) {
        this.available = available;
    }
}
