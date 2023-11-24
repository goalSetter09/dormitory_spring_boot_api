package B911021.Kim.repository;

import B911021.Kim.entity.Washer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WasherRepository extends JpaRepository<Washer, Long> {

    Optional<Washer> findByNumber(int number);

}
