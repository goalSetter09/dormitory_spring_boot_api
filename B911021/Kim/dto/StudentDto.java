package B911021.Kim.dto;

import lombok.Data;

@Data
public class StudentDto {
    private String studentNumber;
    private String name;
    private String phone;
    private int roomNum;

    // 디폴트 생성자
    public StudentDto() {
    }

    // 필드를 이용한 생성자
    public StudentDto(String studentNumber, String name, String phone, int roomNum) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.roomNum = roomNum;
    }
}