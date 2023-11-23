package B911021.Kim.repository;

import B911021.Kim.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByName(String name);

    Optional<Student> findByStudentNumber(String studentNumber);
}
