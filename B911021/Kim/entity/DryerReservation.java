package B911021.Kim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DryerReservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        student.updateDryerStudentStatusToRESERVE();
        dryer.getDryerReservations().add(this);
        dryer.updateAvailable(false);
    }
}
