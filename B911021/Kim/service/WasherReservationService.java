package B911021.Kim.service;

import B911021.Kim.entity.Student;
import B911021.Kim.entity.Washer;
import B911021.Kim.entity.WasherReservation;
import B911021.Kim.repository.WasherReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasherReservationService {

    private final WasherReservationRepository washerReservationRepository;


    public WasherReservation reserveWasher(Student student, Washer washer) {
        if(washer.isAvailable()) {
            WasherReservation washerReservation = new WasherReservation(LocalDateTime.now(), student, washer);
            return washerReservationRepository.save(washerReservation);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Washer cancelWasherReservation(Student student, Washer washer) {
        Optional<WasherReservation> byStudentAndWasher = washerReservationRepository.findByStudentAndWasher(student, washer);
        if (byStudentAndWasher.isPresent()) {
            WasherReservation washerReservation = byStudentAndWasher.get();
            student.cancelWasherReservation(washerReservation);
            washer.cancel(washerReservation);
            return washer;
        } else {
            throw new NoSuchElementException("해당 세탁기에 대한 예약이 존재하지 않습니다.");
        }
    }


}
