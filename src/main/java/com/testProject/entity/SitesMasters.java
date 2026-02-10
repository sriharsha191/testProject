package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "SITES_MASTERS")
@Data
public class SitesMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APARTMENT_NAME")
    private String apartmentName;

    @Column(name = "ASSOCIATED_COMPANY_ID")
    private Long associatedCompanyId=0L;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NO_OF_FLOORS")
    private Long noOfFloors=0L;

    @Column(name = "LIFT_MODEL")
    private String liftModel;

    @Column(name = "PRIMARY_CONTACT_NAME")
    private String primaryContactName;

    @Column(name = "PRIMARY_CONTACT_MOBILE_NUMBER")
    private String primaryContactMobileNumber;

    @Column(name = "SECONDARY_CONTACT_NAME")
    private String secondaryContactName;

    @Column(name = "SECONDARY_CONTACT_MOBILE_NUMBER")
    private String secondaryContactMobileNumber;

    @Column(name = "TERTIARY_CONTACT_NAME")
    private String tertiaryContactName;

    @Column(name = "TERTIARY_CONTACT_MOBILE_NUMBER")
    private String tertiaryContactMobileNumber;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "AMC_TYPE_ID")
    private Long amcTypeId;

    @Column(name = "PAYMENT_TYPE_ID")
    private Long paymentTypeId;

    @Column(name ="BUTTON_MODEL_ID")
    private Long buttonModelId;

    @Column(name = "OTHER_BUTTON_MODEL")
    private String otherButtonModel;

    @Column(name = "BUTTON_MODEL_IMAGE")
    private String buttonModelImage;

    @Column(name = "MOTOR_TYPE_ID")
    private Long motorTypeId;

    @Column(name = "OTHER_MOTOR_MODEL")
    private String otherMotorModel;

    @Column(name = "OTHER_MOTOR_MODEL_IMAGE")
    private String otherMotorModelImage;

    @Column(name = "CONTROLLER_TYPE_ID")
    private Long controllerTypeId;

    @Column(name = "OTHER_CONTROLLER_TYPE")
    private String otherControllerType;

    @Column(name = "CONTROLLER_MODEL_IMAGE")
    private String controllerModelImage;

    @Column(name = "DRIVE_TYPE_ID")
    private Long driveTypeId;

    @Column(name = "OTHER_DRIVE_TYPE")
    private String otherDriveType;

    @Column(name = "DRIVE_TYPE_IMAGE")
    private String driveTypeImage;

    @Column(name = "LIFT_AGE")
    private Integer liftAge=0;

    @Column(name = "LIFT_MAKE")
    private String liftMake;

    @Column(name = "OTHERS")
    private String others;

    @Column(name ="ACTIVE")
    private boolean active=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();
}
