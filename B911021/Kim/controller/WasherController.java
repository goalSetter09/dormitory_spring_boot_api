package B911021.Kim.controller;

import B911021.Kim.dto.WasherDto;
import B911021.Kim.dto.WasherReservationDto;
import B911021.Kim.entity.Student;
import B911021.Kim.entity.Washer;
import B911021.Kim.service.StudentService;
import B911021.Kim.service.WasherReservationService;
import B911021.Kim.service.WasherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/washer")
public class WasherController {

    private final StudentService studentService;
    private final WasherService washerService;
    private final WasherReservationService washerReservationService;

    @GetMapping("list")
    public List<WasherDto> getAvailableWashers() {
        try {
            List<Washer> availableWashers = washerService.findAvailableWashers();
            List<WasherDto> washerDtos = availableWashers.stream().map(washer -> new WasherDto(washer)).toList();
            return washerDtos;
        } catch (IllegalStateException e) {
            List<WasherDto> nullList = new ArrayList<>();
            return nullList;
        }
    }

    @PostMapping("/create")
    public ResponseEntity<WasherDto> createWasher(@RequestBody int number) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            washerService.createWasher(number);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<WasherDto> deleteWasher(@RequestBody int number) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            washerService.deleteWasher(number);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveWasher(@RequestBody WasherReservationDto washerReservationDto) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            Washer washer = washerService.findByWasherNumber(washerReservationDto.getWasherNumber());
            Student student = studentService.findByStudentNumber(washerReservationDto.getStudentNumber());
            washerReservationService.reserveWasher(student, washer);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/reserve/cancel")
    public ResponseEntity<?> cancelWasherReservation(@RequestBody WasherReservationDto washerReservationDto) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            Washer washer = washerService.findByWasherNumber(washerReservationDto.getWasherNumber());
            Student student = studentService.findByStudentNumber(washerReservationDto.getStudentNumber());
            washerReservationService.cancelWasherReservation(student, washer);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

}

//{
//    "studentNumber": "B911021",
//    "name": "김동영",
//    "phone": "010-9057-0578",
//    "roomNum": 1027
//}

//{
//    "studentNumber": "B911021",
//    "washerNumber": 1
//}