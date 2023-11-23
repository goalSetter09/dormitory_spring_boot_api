//package B911021.Kim.Service;
//
//import B911021.Kim.dto.StudentDto;
//import B911021.Kim.entity.Student;
//import B911021.Kim.repository.StudentRepository;
//import B911021.Kim.repository.WasherRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class WasherService {
//
//    private static WasherRepository washerRepository;
//
//    public Long CreateWasher(WasherDto washerDto) {
//        Student student = new Student(
//                studentDto.getId(),
//                studentDto.getName(),
//                studentDto.getPhone(),
//                studentDto.getBirthDate(),
//                studentDto.getPassword()
//        );
//        studentRepository.save(student);
//    }
//
//    public List<StudentDto> getStudents() {
//        List<Student> students = studentRepository.findAll();
//        return students.stream().map(entity -> new StudentDto(entity)).toList();
//    }
//
//    public Student GetStudentById(Long id) {
//        Optional<Student> byId = studentRepository.findById(id);
//        if(byId.isPresent()) {
//            return byId.get();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getToDoById - to do not found");
//        }
//
//
//    }