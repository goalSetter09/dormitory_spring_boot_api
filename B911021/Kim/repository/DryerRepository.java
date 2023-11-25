package B911021.Kim.repository;

import B911021.Kim.entity.Dryer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DryerRepository extends JpaRepository<Dryer, Long> {

    Optional<Dryer> findByNumber(int number);

    List<Dryer> findAllByAvailable(boolean available);

}
