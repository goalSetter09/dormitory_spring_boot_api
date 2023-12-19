package B911021.Kim.controller;

import B911021.Kim.dto.DryerDto;
import B911021.Kim.dto.DryerReservationDto;
import B911021.Kim.entity.Dryer;
import B911021.Kim.entity.Student;
import B911021.Kim.service.DryerReservationService;
import B911021.Kim.service.DryerService;
import B911021.Kim.service.StudentService;
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
@RequestMapping("/dryer")
public class DryerController {

    private final StudentService studentService;
    private final DryerService dryerService;
    private final DryerReservationService dryerReservationService;

    @GetMapping("/list")
    public List<DryerDto> getAvailableDryer() {
        try {
            List<Dryer> availableWashers = dryerService.findAvailableDryers();
            List<DryerDto> dryerDtos = availableWashers.stream().map(dryer -> new DryerDto(dryer)).toList();
            return dryerDtos;
        } catch (IllegalStateException e) {
            List<DryerDto> nullList = new ArrayList<>();
            return nullList;
        }
    }

    @PostMapping("/create")
    public ResponseEntity<DryerDto> createDryer(@RequestBody int number) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            dryerService.createDryer(number);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<DryerDto> deleteWasher(@RequestBody int number) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            dryerService.deleteDryer(number);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveDryer(@RequestBody DryerReservationDto dryerReservationDto) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            Dryer dryer = dryerService.findByDryerNumber(dryerReservationDto.getDryerNumber());
            Student student = studentService.findByStudentNumber(dryerReservationDto.getStudentNumber());
            dryerReservationService.reserveDryer(student, dryer);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/reserve/cancel")
    public ResponseEntity<?> cancelWasherReservation(@RequestBody DryerReservationDto dryerReservationDto) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            Dryer dryer = dryerService.findByDryerNumber(dryerReservationDto.getDryerNumber());
            Student student = studentService.findByStudentNumber(dryerReservationDto.getStudentNumber());
            dryerReservationService.cancelDryerReservation(student, dryer);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }
}
