package com.testProject.dto;

import com.testProject.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CompanyCreationDTO {

    @NotBlank(message = Constants.Messages.emptyEmailId)
    private String emailId;

    @NotBlank(message = Constants.Messages.emptyCompanyName)
    private String companyName;

    @NotBlank(message = "Null or Empty Company Admin Email Id")
    private String adminEmailId;

    @NotBlank(message = "Null or Empty Company Admin Name")
    private String adminName;

    @NotBlank(message = "Null or Empty Admin Mobile Number")
    private String adminMobileNumber;

    @NotBlank(message = Constants.Messages.emptyGstNumber )
    private String gstNumber;

    private String gstImage;

    @NotBlank(message = Constants.ValidationMessages.nullOrEmptyAddress)
    private String address;

    @NotNull(message = "Null or Empty Package Type")
    private Long packageType;

    @NotBlank
    private String primaryContactName;

    @NotBlank
    private String primaryContactMobileNumber;

    private String secondaryContactName;

    private String secondaryContactMobileNumber;

    @NotBlank
    private String companyImage;

    private String streetImage;

    private String buildingImage;

    @NotBlank
    private String location;

    private String landLineNumber;

    private String visitingCardImage;
}
