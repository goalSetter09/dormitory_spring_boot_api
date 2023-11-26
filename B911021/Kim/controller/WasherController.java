package B911021.Kim.controller;

import B911021.Kim.dto.WasherDto;
import B911021.Kim.entity.Washer;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/washer")
public class WasherController {

    private final WasherService washerService;

    @GetMapping("")
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
        washerService.createWasher(number);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<WasherDto> deleteWasher(@RequestBody int number) {
        washerService.deleteWasher(number);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
