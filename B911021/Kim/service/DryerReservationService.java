package B911021.Kim.service;

import B911021.Kim.entity.*;
import B911021.Kim.repository.DryerReservationRepository;
import B911021.Kim.repository.WasherReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DryerReservationService {

    private final DryerReservationRepository dryerReservationRepository;

    public DryerReservation reserveDryer(Student student, Dryer dryer) {
        if(dryer.isAvailable()) {
            DryerReservation dryerReservation = new DryerReservation(LocalDateTime.now(), student, dryer);
            return dryerReservationRepository.save(dryerReservation);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Dryer cancelDryerReservation(Student student, Dryer dryer) {
        Optional<DryerReservation> byStudentAndDryer = dryerReservationRepository.findByStudentAndDryer(student, dryer);
        if (byStudentAndDryer.isPresent()) {
            DryerReservation DryerReservation = byStudentAndDryer.get();
            student.cancelDryerReservation(DryerReservation);
            dryer.cancel(DryerReservation);
            return dryer;
        } else {
            throw new NoSuchElementException("해당 세탁기에 대한 예약이 존재하지 않습니다.");
        }
    }

}
