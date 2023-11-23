package B911021.Kim.repository;

import B911021.Kim.entity.Student;
import B911021.Kim.entity.Washer;
import B911021.Kim.entity.WasherReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasherReservationRepository extends JpaRepository<WasherReservation, Long> {
    WasherReservation findByStudent(Student student);
}
