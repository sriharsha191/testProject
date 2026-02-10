package com.testProject.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "EMPLOYEE_ATTENDANCE_HISTORY")
@Data
public class EmployeeAttendanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMPLOYEE_EMAIL_ID")
    private String employeeEmailId;

    @Column(name = "ATTENDANCE_DATE")
    private LocalDate attendanceDate;

    @Column(name = "CHECK_IN_TIME")
    private LocalDateTime checkInTime;

    @Column(name = "CHECK_OUT_TIME")
    private LocalDateTime checkOutTime;

    @Column(name = "CHECK_IN_LATITUDE")
    private Double checkInLatitude;

    @Column(name = "CHECK_IN_LONGITUDE")
    private Double checkInLongitude;

    @Column(name = "CHECK_OUT_LATITUDE")
    private Double checkOutLatitude;

    @Column(name = "CHECK_OUT_LONGITUDE")
    private Double checkOutLongitude;

    @Column(name = "STATUS")
    private boolean status=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name = "MODIFIED_ON")
    private LocalDateTime modifiedOn;
}
