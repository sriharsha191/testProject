package com.testProject.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SiteCreationDTO {

    private Long id=0L;

    @NotBlank(message = "Null or Empty Email Id Value")
    private String emailId;

    @NotBlank(message ="Null or Empty Apartment Value")
    private String apartmentName;

    @NotNull(message ="Null or Empty Associated Company Id")
    private Long associatedCompanyId=0L;

    @NotBlank(message = "Null or Empty Address")
    private String address;

    @NotNull(message = "Null or Empty No Of Floors Value")
    private Long noOfFloors=0L;

    @NotBlank(message = "Null or Empty Lift Model Value")
    private String liftModel;

    @NotBlank(message = "Null or Empty Primary Contact Name Value")
    private String primaryContactName;

    @NotBlank(message ="Null or Empty Primary Contact Mobile Number")
    private String primaryContactMobileNumber;


    private String secondaryContactName;

    private String secondaryContactMobileNumber;

    private String tertiaryContactName;

    private String tertiaryContactMobileNumber;

    @NotBlank(message = "Null or Empty Location Value")
    private String location;

    @NotNull(message = "Null or Empty AMC Type value")
    private Long amcTypeId =0L;

    @NotNull(message = "Null or Empty Payment Type value")
    private Long paymentTypeId =0L;

    @NotNull(message = "Button Model Value")
    private Long buttonModelId =0L;


    private String otherButtonModel;

    @NotBlank(message = "Null or Empty Button Mode Image")
    private String buttonModelImage;

    @NotNull(message = "Null or Empty Motor Type")
    private Long motorTypeId =0L;


    private String otherMotorModel;

    private String otherMotorModelImage;

    @NotNull(message = "Null or Empty Controller Type")
    private Long controllerTypeId =0L;

    private String otherControllerType;

    @NotBlank(message = "Null or Empty Controller Model Image")
    private String controllerModelImage;

    @NotNull(message = "Null or Empty Device Type")
    private Long driveTypeId =0L;


    private String otherDriveType;

    @NotBlank(message = "Null or Empty Drive Type Image")
    private String driveTypeImage;

    @NotNull(message = "Null or Empty Lift Age")
    private Integer liftAge=0;

    @NotBlank(message = "Null or Empty Lift Make Value")
    private String liftMake;

    private String others;
}
