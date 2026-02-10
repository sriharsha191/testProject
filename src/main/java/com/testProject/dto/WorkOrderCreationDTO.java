package com.testProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class WorkOrderCreationDTO {

    private Long id=0L;

    @NotBlank(message = "Null or Empty Email Id")
    private String emailId;

    @NotNull(message = "Null or Empty Apartment Id")
    private Long apartmentId;

    @NotBlank(message = "Null or Empty Complaint By Value")
    private String complaintBy;

    @NotBlank(message = "Null or Empty Mobile Number")
    private String mobileNumber;

    @NotNull(message = "Null or Empty Work Type Id")
    private Long workTypeId;


    private String others;

    @NotNull(message = "Null or Empty Support Employee Ids List")
    private List<Long> supportEmployeeIds;

    @NotBlank(message = "Null or Empty Work Created By Value")
    private String workCreatedBy;

    @NotNull(message = "Null or Empty Work Assigned Date")
    private LocalDate workAssignedDate;

}
