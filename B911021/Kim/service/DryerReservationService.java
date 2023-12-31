package B911021.Kim.service;

import B911021.Kim.entity.*;
import B911021.Kim.repository.DryerReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DryerReservationService {

    private final DryerReservationRepository dryerReservationRepository;

    @Transactional
    public DryerReservation reserveDryer(Student student, Dryer dryer) {
        if(dryer.isAvailable() && student.getDryerStudentStatus().equals(DryerStudentStatus.NONE)) {
            DryerReservation dryerReservation = new DryerReservation(LocalDateTime.now(), student, dryer);
            return dryerReservationRepository.save(dryerReservation);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public Dryer cancelDryerReservation(Student student, Dryer dryer) {
        Optional<DryerReservation> byStudentAndDryer = dryerReservationRepository.findByStudentAndDryer(student, dryer);
        if (byStudentAndDryer.isPresent()) {
            DryerReservation dryerReservation = byStudentAndDryer.get();
            dryerReservation.updateReservationStatusToCANCELED();
            student.cancelDryerReservation(dryerReservation);
            dryer.cancel(dryerReservation);
            return dryer;
        } else {
            throw new NoSuchElementException("해당 세탁기에 대한 예약이 존재하지 않습니다.");
        }
    }

}
