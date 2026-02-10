package com.testProject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeCreationDTO {

    @NotBlank(message = "Null or Empty Admin Email Id")
    private String adminEmailId;


    @NotBlank(message = "Null or Empty Employee Name")
    private String employeeName;

    @NotNull(message = "Null Role Id")
    private Long roleId;

    @NotBlank(message = "Null or Empty Email Id")
    private String emailId;

    @NotBlank(message = "Null or Empty Mobile Number")
    private String mobileNumber;

    private String alternativeMobileNumber;

    @NotBlank(message = "Null or Empty Employee Profile Image")
    private String employeeProfileImage;

    @NotBlank(message = "Null or Empty Date Of Birth")
    private String dateOfBirth;

    @NotBlank(message = "SNull or Empty Primary Family Member Name")
    private String primaryFamilyMemberName;

    @NotBlank(message = "Null or Empty Primary Family Member Mobile Number")
    private String primaryFamilyMemberMobileNumber;

    private String secondaryFamilyMemberName;

    private String secondaryFamilyMemberMobileNumber;

    @NotBlank(message = "Null or Empty Date Of Joining")
    private LocalDate dateOfJoining;

    @NotBlank(message = "Null or Empty Employee Type")
    private Long employeeType;

    @NotBlank(message = "Null or Empty Permanent Address")
    private String permanentAddress;

    @NotBlank(message = "Null or Empty Current Address")
    private String currentAddress;


    private String aadharCardImageUrl;

    @NotBlank(message = "Null or Empty Bank Account Number")
    private String bankAccountNumber;

    @NotBlank(message = "Null or Empty UPI Mobile Number")
    private String upiMobileNumber;

    @NotNull(message = "Empty Spoken Languages")
    private List<Long> spokenLanguages;

    @NotBlank(message = "Null or Empty Blood Group")
    private String bloodGroup;


}
