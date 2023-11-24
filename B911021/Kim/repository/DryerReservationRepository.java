package B911021.Kim.repository;
import B911021.Kim.entity.Dryer;
import B911021.Kim.entity.DryerReservation;
import B911021.Kim.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DryerReservationRepository extends JpaRepository<DryerReservation, Long> {

    DryerReservation findByStudent(Student student);

    DryerReservation findByDryer(Dryer dryer);
}
