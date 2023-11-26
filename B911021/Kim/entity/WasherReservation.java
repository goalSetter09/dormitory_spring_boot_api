package B911021.Kim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class WasherReservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime rTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wid")
    private Washer washer;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약의 상태 [RESERVED, CANCELED]

    //==생성 매서드==//
    public WasherReservation(LocalDateTime rTime, Student student, Washer washer) {
        this.rTime = rTime;
        this.student = student;
        this.washer = washer;
        this.reservationStatus = ReservationStatus.RESERVED;
        student.getWasherReservations().add(this);
        student.updateWasherStudentStatusToRESERVE();
        washer.getWasherReservations().add(this);
        washer.updateAvailable(false);
    }

    //예약 상태 변경 매서드
    public void updateReservationStatusToCANCELED() {
        this.reservationStatus = ReservationStatus.CANCELED;
    }
}
