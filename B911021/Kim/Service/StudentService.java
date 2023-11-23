package B911021.Kim.Service;

import B911021.Kim.dto.StudentDto;
import B911021.Kim.entity.Student;
import B911021.Kim.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static B911021.Kim.entity.Student.toStudentEntity;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent(StudentDto studentDto) {
        Student student = toStudentEntity(studentDto);
        return studentRepository.save(student);
    }

    public Student updateStudent(StudentDto studentDto) {
        Optional<Student> byStudentNumber = studentRepository.findByStudentNumber(studentDto.getStudentNumber());
    }

//    private validateDuplicateStudent() {
//
//    }
}
