package B911021.Kim.service;

import B911021.Kim.dto.StudentDto;
import B911021.Kim.entity.Student;
import B911021.Kim.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static B911021.Kim.entity.Student.toStudentEntity;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Student createStudent(StudentDto studentDto) {
        if (validateDuplicatedStudent(studentDto)) {
            Student student = toStudentEntity(studentDto);
            return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException();
        }
    }

    //같은 학번으로 이미 존재하는 학생 정보가 있으면 false 리턴
    private boolean validateDuplicatedStudent(StudentDto studentDto) {
        Optional<Student> byStudentNumber = studentRepository.findByStudentNumber(studentDto.getStudentNumber());
        if (byStudentNumber.isPresent()) {
            return false;
        }
        return true;
    }

    @Transactional
    public Student updateStudent(StudentDto studentDto) {
        Optional<Student> byStudentNumber = studentRepository.findByStudentNumber(studentDto.getStudentNumber());

        if (byStudentNumber.isPresent()) {
            Student student = byStudentNumber.get();
            return student.updateStudent(studentDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public String deleteStudent(StudentDto studentDto) {
        Optional<Student> byStudentNumber = studentRepository.findByStudentNumber(studentDto.getStudentNumber());
        if (byStudentNumber.isPresent()) {
            Student student = byStudentNumber.get();
            studentRepository.delete(student);
            return student.getStudentNumber();
        } else {
            throw new NullPointerException();
        }
    }

    public Student findByStudentNumber(String studentNumber) {
        Optional<Student> byStudentNumber = studentRepository.findByStudentNumber(studentNumber);
        if (byStudentNumber.isPresent()) {
            return byStudentNumber.get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
