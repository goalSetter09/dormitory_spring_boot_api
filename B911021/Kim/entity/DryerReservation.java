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
public class DryerReservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_rid")
    private int rid;

    @Column
    private LocalDateTime rTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "did")
    private Dryer dryer;

    //==생성 매서드==//
    public DryerReservation(LocalDateTime rTime, Student student, Dryer dryer) {
        this.rTime = rTime;
        this.student = student;
        this.dryer = dryer;
        student.getDryerReservations().add(this);
        dryer.getDryerReservations().add(this);
    }
}
