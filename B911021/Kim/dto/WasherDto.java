package B911021.Kim.dto;

import B911021.Kim.entity.Washer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WasherDto {

    private int number;


    public WasherDto(Washer washer) {
        this.number = washer.getNumber();
    }
}
