package B911021.Kim.dto;

import B911021.Kim.entity.Dryer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DryerDto {

    private int number;

    public DryerDto(Dryer dryer) {
        this.number = dryer.getNumber();
    }
}
