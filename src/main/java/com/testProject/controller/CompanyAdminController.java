package com.testProject.controller;

import com.testProject.dto.EmployeeCreationDTO;
import com.testProject.dto.ResponseDTO;
import com.testProject.dto.SiteCreationDTO;
import com.testProject.service.CompanyAdminService;
import com.testProject.service.FileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/restAPI/companyAdmin")
public class CompanyAdminController {

    @Autowired
    private CompanyAdminService companyAdminService;

    @Autowired
    private FileUploadService  fileUploadService;

    @PostMapping(value = "/siteCreation")
    public ResponseDTO siteCreation(HttpServletRequest request, @RequestBody @Valid SiteCreationDTO siteCreationDTO)
    {
        return companyAdminService.saveOrUpdateSiteDetails(request,siteCreationDTO);
    }

    @PostMapping(value = "/getSitesList")
    public ResponseDTO getSitesList(HttpServletRequest request,@RequestBody String jsonData)
    {
        return companyAdminService.getCompanyWiseSitesList(request, jsonData);
    }

    @PostMapping(value = "/addEmployee")
    public ResponseDTO addEmployee(HttpServletRequest request,@Valid @RequestBody EmployeeCreationDTO employeeCreationDTO)
    {
        return companyAdminService.addEmployee(request, employeeCreationDTO);
    }

    @PostMapping(value = "/getEmployeeList")
    public ResponseDTO getEmployeeList(HttpServletRequest request,@RequestBody String jsonData)
    {
        return companyAdminService.companyWiseEmployeeList(request, jsonData);
    }

    @PostMapping(value = "/uploadFile")
    public ResponseDTO uploadFile(@Param("file") MultipartFile file,@Param("emailId") String emailId)
    {
        return  fileUploadService.uploadFile(file,emailId);
    }

//    public ResponseDTO
}
