package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE_MASTER")
@Data
public class EmployeeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "ALTERNATIVE_MOBILE_NUMBER")
    private String alternativeMobileNumber;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "EMPLOYEE_PROFILE_IMAGE")
    private String employeeProfileImage;

    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;

    @Column(name = "PRIMARY_MEMBER_NAME")
    private String primaryFamilyMemberName;

    @Column(name = "PRIMARY_FAMILY_MEMBER_MOBILE_NUMBER")
    private String primaryFamilyMemberMobileNumber;

    @Column(name = "SECONDARY_FAMILY_MEMBER_NAME")
    private String secondaryFamilyMemberName;

    @Column(name = "SECONDARY_FAMILY_MEMBER_MOBILE_NUMBER")
    private String secondaryFamilyMemberMobileNumber;

    @Column(name = "DATE_OF_JOINING")
    private LocalDate dateOfJoining;

    @Column(name = "EMPLOYEE_TYPE")
    private Long employeeType;

    @Column(name = "PERMANENT_ADDRESS")
    private String permanentAddress;

    @Column(name = "CURRENT_ADDRESS")
    private String currentAddress;

    @Column(name = "AADHAR_CARD_IMAGE_URL")
    private String aadharCardImageUrl;

    @Column(name = "BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    @Column(name = "UPI_MOBILE_NUMBER")
    private String upiMobileNumber;

    @Column(name = "SPOKEN_LANGUAGES")
    private List<Long> spokenLanguages;

    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name ="CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();
}
