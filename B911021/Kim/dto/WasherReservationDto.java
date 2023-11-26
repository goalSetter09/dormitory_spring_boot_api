package B911021.Kim.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WasherReservationDto {

    private String studentNumber;
    private int washerNumber;

    public WasherReservationDto(String studentNumber, int washerNumber) {
        this.studentNumber = studentNumber;
        this.washerNumber = washerNumber;
    }
}
