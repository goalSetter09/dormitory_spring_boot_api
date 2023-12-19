package B911021.Kim.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DryerReservationDto {

    private String studentNumber;
    private int dryerNumber;

    public DryerReservationDto(String studentNumber, int dryerNumber) {
        this.studentNumber = studentNumber;
        this.dryerNumber = dryerNumber;
    }
}
