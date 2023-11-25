package B911021.Kim.repository;
import B911021.Kim.entity.Dryer;
import B911021.Kim.entity.DryerReservation;
import B911021.Kim.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DryerReservationRepository extends JpaRepository<DryerReservation, Long> {

    List<DryerReservation> findAllByStudent(Student student);

    List<DryerReservation> findAllByDryer(Dryer dryer);

    Optional<DryerReservation> findByStudentAndDryer(Student student, Dryer dryer);
}
