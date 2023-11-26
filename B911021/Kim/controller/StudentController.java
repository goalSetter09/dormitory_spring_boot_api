package B911021.Kim.controller;

import B911021.Kim.dto.StudentDto;
import B911021.Kim.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

//    @PostMapping("/create")
//    public ResponseEntity<StudentDto> CreateStudent(@RequestBody StudentDto studentDto) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        try {
//            System.out.println("studentDto = " + studentDto);
//            studentService.createStudent(studentDto);
//            return new ResponseEntity<>(headers, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
//        } finally {
//            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
//        }
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            studentService.createStudent(studentDto);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // 다른 예외를 처리할 필요가 있다면 여기에 추가
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDto studentDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            studentService.updateStudent(studentDto);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStudent(@RequestBody StudentDto studentDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            System.out.println("studentDto = " + studentDto);
            studentService.deleteStudent(studentDto);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



/*
        private String studentNumber;
        private String name;
        private String phone;
        private int roomNum;
    {
        "studentNumber" : "B911021",
        "name" : "김동영",
        "phone" : "010-9057-0578",
        "roomNum" : 1027"
    }
 */