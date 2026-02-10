package com.testProject.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceHistoryDTO {

    private String emailId;

    private String employeeName;

    private LocalDate attendanceDate;

    private String formattedAttendanceDate;

    private LocalDateTime checkInTime;

    private String formattedCheckInTime;

    private LocalDateTime checkOutTime;

    private String formattedCheckOutTime;

    private Double checkInLatitude;

    private Double checkInLongitude;

    private Double checkOutLatitude;

    private Double checkOutLongitude;
}
