package com.testProject.controller;

import com.testProject.dto.CompanyCreationDTO;
import com.testProject.dto.ResponseDTO;
import com.testProject.service.SuperAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restAPI/superAdmin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @PostMapping(value = "/createCompany")
    public ResponseDTO createCompany(HttpServletRequest request,  @Valid @RequestBody CompanyCreationDTO companyCreationDTO)
    {
        return superAdminService.createCompany(request, companyCreationDTO);
    }

    @PostMapping(value = "/getCompaniesList")
    public ResponseDTO getCompaniesList(HttpServletRequest request,@RequestBody String jsonData)
    {
        return superAdminService.companiesList(request,jsonData);
    }
}
