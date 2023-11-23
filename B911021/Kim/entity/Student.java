package B911021.Kim.entity;

import B911021.Kim.dto.StudentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Column
    private String studentNumber;

    @Column
    private String name;

    @Column
    private String phone;

    @Column(name = "room_number")
    private int roomNum;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<DryerReservation> dryerReservations = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<WasherReservation> washerReservations = new ArrayList<>();

    public Student(String studentNumber, String name, String phone, int roomNum) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.roomNum = roomNum;
    }

    public static Student toStudentEntity(StudentDto studentDto) {
        return new Student(studentDto.getStudentNumber(),
                studentDto.getName(),
                studentDto.getPhone(),
                studentDto.getRoomNum());
    }
}
