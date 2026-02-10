package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMPANY_MASTERS")
@Data
public class CompanyMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "ADMIN_EMAIL_ID")
    private String adminEmailId;

    @Column(name = "GST_NUMBER")
    private String gstNumber;

    @Column(name = "GST_IMAGE")
    private String gstImage;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PACKAGE_TYPE")
    private Long packageType;

    @Column(name = "PACKAGE_NAME")
    private String packageName;

    @Column(name = "PRIMARY_CONTACT_NAME")
    private String primaryContactName;

    @Column(name = "PRIMARY_CONTACT_MOBILE_NUMBER")
    private String primaryContactMobileNumber;

    @Column(name = "SECONDARY_CONTACT_NAME")
    private String secondaryContactName;

    @Column(name = "SECONDARY_CONTACT_MOBILE_NUMBER")
    private String secondaryContactMobileNumber;

    @Column(name = "COMPANY_IMAGE")
    private String companyImage;

    @Column(name = "STREET_IMAGE")
    private String streetImage;

    @Column(name = "BUILDING_IMAGE")
    private String buildingImage;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "LAND_LINE_NUMBER")
    private String landLineNumber;

    @Column(name = "VISITING_CARD_IMAGE")
    private String visitingCardImage;

    @Column(name = "ACTIVE")
    private boolean active=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();

}
