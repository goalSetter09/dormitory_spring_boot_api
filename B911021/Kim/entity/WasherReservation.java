package B911021.Kim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class WasherReservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_rid")
    private int id;

    @Column
    private LocalDateTime rTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wid")
    private Washer washer;

    //==생성 매서드==//
    public WasherReservation(LocalDateTime rTime, Student student, Washer washer) {
        this.rTime = rTime;
        this.student = student;
        this.washer = washer;
        student.getWasherReservations().add(this);
        washer.getWasherReservations().add(this);
    }
}
