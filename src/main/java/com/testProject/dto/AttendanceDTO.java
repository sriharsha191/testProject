package com.testProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class AttendanceDTO {

    @NotBlank(message = "Null or Empty Email Id")
    private String emailId;

    @NotNull(message = "Null or Empty Flag Value")
    private int flag=0;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    @NotNull(message = "Null or Empty Latitude value")
    private Double latitude;

    @NotNull(message = "Null or Empty Longitude value")
    private Double longitude;


}
