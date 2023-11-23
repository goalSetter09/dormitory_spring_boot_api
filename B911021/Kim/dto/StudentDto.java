package B911021.Kim.dto;

import B911021.Kim.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    private String studentNumber;
    private String name;
    private String phone;
    private int roomNum;
}
