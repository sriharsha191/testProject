package com.testProject.dto;

import lombok.Data;

@Data
public class CompanyListDTO {

    private Long companyId;

    private String companyName;

    private String adminEmailId;

    private String gstNumber;

    private String gstImage;

    private String address;

    private Long packageTypeId;

    private String packageName;

    private String primaryContactName;

    private String primaryContactMobileNumber;

    private String secondaryContactName;

    private String secondaryContactMobileNumber;

    private String companyImage;

    private String streetImage;

    private String buildingImage;

    private String location;

    private String landLineNumber;

    private String visitingCardImage;

    private String createdAt;

}
