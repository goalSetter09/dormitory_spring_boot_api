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

    @Enumerated(EnumType.STRING)
    private WasherStudentStatus washerStudentStatus; // 세탁기에 대한 학생상태 [NONE, RESERVE]

    @Enumerated(EnumType.STRING)
    private DryerStudentStatus dryerStudentStatus; // 세탁기에 대한 학생상태 [NONE, RESERVE]

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

    //학생정보 수정 매서드
    public Student updateStudent(StudentDto studentDto) {
        this.name = studentDto.getName();
        this.phone = studentDto.getPhone();
        this.roomNum = studentDto.getRoomNum();
        return this;
    }

    //학생의 상태를 예약중으로 변경
    public void updateWasherStudentStatusToRESERVE() {
        this.washerStudentStatus = washerStudentStatus.RESERVE;
    }
    public void updateDryerStudentStatusToRESERVE() {
        this.dryerStudentStatus = dryerStudentStatus.RESERVE;
    }

    //예약 취소 매서드
    public void cancelWasherReservation(WasherReservation washerReservation) {
        if(this.washerStudentStatus != washerStudentStatus.RESERVE) {
            throw new IllegalStateException("예약중인 상태가 아닙니다.");
        }
        this.washerStudentStatus = washerStudentStatus.NONE;
        this.washerReservations.remove(washerReservation);
    }

    public void cancelDryerReservation(DryerReservation dryerReservation) {
        if(this.dryerStudentStatus != dryerStudentStatus.RESERVE) {
            throw new IllegalStateException("예약중인 상태가 아닙니다.");
        }
        this.dryerStudentStatus = dryerStudentStatus.NONE;
        this.dryerReservations.remove(dryerReservation);
    }
}
