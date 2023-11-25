package B911021.Kim.repository;

import B911021.Kim.entity.Student;
import B911021.Kim.entity.Washer;
import B911021.Kim.entity.WasherReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface WasherReservationRepository extends JpaRepository<WasherReservation, Long> {

    List<WasherReservation> findAllByStudent(Student student);

    List<WasherReservation> findAllByWasher(Washer washer);

    Optional<WasherReservation> findByStudentAndWasher(Student student, Washer washer);
}
