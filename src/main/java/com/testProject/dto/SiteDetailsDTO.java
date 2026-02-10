package com.testProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SiteDetailsDTO {

    private Long id;

    private String apartmentName;

    private Long associatedCompanyId=0L;

    private String associatedCompanyName;

    private String address;

    private Long noOfFloors=0L;

    private String liftModel;

    private String primaryContactName;

    private String primaryContactMobileNumber;

    private String secondaryContactName;

    private String secondaryContactMobileNumber;

    private String tertiaryContactName;

    private String tertiaryContactMobileNumber;

    private String location;

    private Long amcTypeId;

    private String amcType;

    private Long paymentTypeId;

    private String paymentType;

    private Long buttonModelId;

    private String buttonModel;

    private String otherButtonModel;

    private String buttonModelImage;

    private Long motorTypeId;

    private String motorType;

    private String otherMotorModel;

    private String otherMotorModelImage;

    private Long controllerTypeId;

    private String controllerType;

    private String otherControllerType;

    private String controllerModelImage;

    private Long driveTypeId;

    private String driveType;

    private String otherDriveType;

    private String driveTypeImage;

    private Integer liftAge=0;

    private String liftMake;

    private String others;
}
