package com.testProject.dto;

import com.testProject.entity.RoleMenuMapping;
import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
public class EmployeeDetailsDTO {

    private Long employeeId;

    private String emailId;

    private String employeeName;

    private String mobileNumber;

    private Long roleId;

    private String role;

    private String companyName;

    private Long companyId;

    private List<MenuControlDTO> menuControl;

    private String alternativeMobileNumber;

    private String employeeProfileImageURL;

    private String dateOfBirth;

    private String primaryFamilyMemberName;

    private String primaryFamilyMemberMobileNumber;

    private String secondaryFamilyMemberName;

    private String secondaryFamilyMemberMobileNumber;

    private String dateOfJoining;

    private Long employeeTypeId;

    private String employeeType;

    private String permanentAddress;

    private String currentAddress;

    private String aadharCardImageUrl;

    private String bankAccountNumber;

    private String upiMobileNumber;

    private Map<Long,String> spokenLanguages;

    private String bloodGroup;


}
