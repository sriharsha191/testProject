package com.testProject.service;

import com.testProject.dto.CompanyCreationDTO;
import com.testProject.dto.CompanyListDTO;
import com.testProject.dto.ResponseDTO;
import com.testProject.entity.CompanyMasters;
import com.testProject.entity.EmployeeMaster;
import com.testProject.repository.CompanyMastersRepository;
import com.testProject.repository.EmployeeMasterRepository;
import com.testProject.utils.Constants;
import com.testProject.utils.DateTimeUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SuperAdminService {

    private static final Logger log= LogManager.getLogger(SuperAdminService.class);

    @Autowired
    private EmployeeMasterRepository employeeMasterRepository;

    @Autowired
    private CompanyMastersRepository companyMastersRepository;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public ResponseDTO createCompany(HttpServletRequest request, CompanyCreationDTO creationRequest)
    {
        log.info("==> createCompany Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {
            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailIdAndActive(creationRequest.getEmailId(),true);

            if(employeeDetails==null)
            {
                log.warn(Constants.Messages.noActiveUserFound);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.noActiveUserFound);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(employeeDetails.getRoleId()!=1)
            {
                log.warn(Constants.Messages.unAuthorized);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.unAuthorized);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            CompanyMasters companyMasters=companyMastersRepository.findByCompanyNameAndAdminEmailIdAndActive(creationRequest.getCompanyName(),creationRequest.getAdminEmailId(),true);

            if(companyMasters!=null)
            {
                log.warn("Company Exists with this Name: {}",creationRequest.getCompanyName());
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg","Company Exists with this Name: "+creationRequest.getCompanyName());
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }


            EmployeeMaster companyAdminDetails=employeeMasterRepository.findByEmailIdAndActive(creationRequest.getAdminEmailId(),true);

            if(companyAdminDetails!=null && !(companyAdminDetails.getCompanyId()==0))
            {
                log.warn("Employee is already assigned to another company");
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg","Employee is already assigned to another company");
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }



            companyMasters=new CompanyMasters();
            companyMasters.setCompanyName(creationRequest.getCompanyName());
            companyMasters.setAdminEmailId(creationRequest.getAdminEmailId());
            companyMasters.setGstNumber(creationRequest.getGstNumber());
            companyMasters.setGstImage(creationRequest.getGstImage());
            companyMasters.setAddress(creationRequest.getAddress());
            companyMasters.setPackageType(1L);
            companyMasters.setPackageName("Package 1");
            companyMasters.setPrimaryContactName(creationRequest.getPrimaryContactName());
            companyMasters.setPrimaryContactMobileNumber(creationRequest.getPrimaryContactMobileNumber());
            companyMasters.setSecondaryContactName(creationRequest.getSecondaryContactName());
            companyMasters.setSecondaryContactMobileNumber(creationRequest.getSecondaryContactMobileNumber());
            companyMasters.setCompanyImage(creationRequest.getCompanyImage());
            companyMasters.setStreetImage(creationRequest.getStreetImage());
            companyMasters.setBuildingImage(creationRequest.getBuildingImage());
            companyMasters.setLocation(creationRequest.getLocation());
            companyMasters.setLandLineNumber(creationRequest.getLandLineNumber());
            companyMasters.setVisitingCardImage(creationRequest.getVisitingCardImage());
            companyMasters.setCreatedAt(LocalDateTime.now());
            companyMasters.setActive(true);
            companyMastersRepository.save(companyMasters);


            companyAdminDetails=new EmployeeMaster();
            companyAdminDetails.setEmailId(creationRequest.getAdminEmailId());
            companyAdminDetails.setCompanyId(companyMasters.getId());
            companyAdminDetails.setEmployeeName(creationRequest.getAdminName());
            companyAdminDetails.setMobileNumber(creationRequest.getAdminMobileNumber());
            companyAdminDetails.setRoleId(2L);
            companyAdminDetails.setPassword(creationRequest.getCompanyName()+"@159357");
            companyAdminDetails.setCreatedAt(LocalDateTime.now());
            companyAdminDetails.setActive(true);
            employeeMasterRepository.save(companyAdminDetails);

            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("msg","Company Created Successfully");
            responseDTO.setResponseObj(respMessages);

        }
        catch (Exception e)
        {
            log.error(Constants.Messages.error,e);
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }

    public ResponseDTO companiesList(HttpServletRequest request,String jsonData)
    {
        log.info("==> companiesList Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try {

            JSONObject jsonObject=new JSONObject(jsonData);
            String emailId=jsonObject.optString("emailId");

            if(emailId==null)
            {
                log.warn(Constants.Messages.emptyEmailId);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.emptyEmailId);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailId(emailId);

            if(employeeDetails==null)
            {
                log.warn(Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            List<CompanyListDTO> companyList=new ArrayList<>();

            List<CompanyMasters> companyMasters=companyMastersRepository.findByActive(true);


            if(!companyMasters.isEmpty())
            {
                companyList.addAll(companyMasters.stream().map(company->{
                    CompanyListDTO dto=new CompanyListDTO();
                    dto.setCompanyId(company.getId());
                    dto.setCompanyName(company.getCompanyName());
                    dto.setAdminEmailId(company.getAdminEmailId());
                    dto.setGstNumber(company.getGstNumber());
                    dto.setGstImage(company.getGstImage());
                    dto.setAddress(company.getAddress());
                    dto.setPackageTypeId(company.getPackageType());
                    dto.setPackageName(company.getPackageName());
                    dto.setPrimaryContactName(company.getPrimaryContactName());
                    dto.setPrimaryContactMobileNumber(company.getPrimaryContactMobileNumber());
                    dto.setSecondaryContactName(company.getSecondaryContactName());
                    dto.setSecondaryContactMobileNumber(company.getSecondaryContactMobileNumber());
                    dto.setCompanyImage(company.getCompanyImage());
                    dto.setStreetImage(company.getStreetImage());
                    dto.setBuildingImage(company.getBuildingImage());
                    dto.setLocation(company.getLocation());
                    dto.setLandLineNumber(company.getLandLineNumber());
                    dto.setVisitingCardImage(company.getVisitingCardImage());
                    dto.setCreatedAt(dateTimeUtil.timeStampFormatter(company.getCreatedAt()));
                    return dto;
                    }).toList());
            }

            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("data",companyList);
            responseDTO.setResponseObj(respMessages);





        } catch (Exception e) {
            log.error(Constants.Messages.error,e);
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }

//    public ResponseDTO assignAdminToCompany(HttpServletRequest request,String jsonData)
//    {
//        ResponseDTO responseDTO=new ResponseDTO();
//        Map<String,Object> respMessages=new HashMap<>();
//        try {
//
//            JSONObject jsonObject=new JSONObject(jsonData);
//            String superAdminEmailId=jsonObject.optString("superAdminEmailId",null);
//            String adminName=jsonObject.optString("adminName",null);
//            String adminEmailId=jsonObject.optString("adminEmailId",null);
//            String adminMobileNumber=jsonObject.optString("adminMobileNumber",null);
//            Long companyId=jsonObject.optLong("companyId",0L);
//
//            if(superAdminEmailId==null)
//            {
//                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_SUPER_ADMIN_EMAIL_ID);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_SUPER_ADMIN_EMAIL_ID);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            if(adminEmailId==null)
//            {
//                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_EMAIL_ID);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_EMAIL_ID);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            if(adminName==null)
//            {
//                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_NAME);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_NAME);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            if(adminMobileNumber==null)
//            {
//                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_MOBILE_NUMBER);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_MOBILE_NUMBER);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            if(companyId==0L)
//            {
//                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_COMPANY_ID);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_COMPANY_ID);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            EmployeeMaster superAdminDetails=employeeMasterRepository.findByEmailIdAndActive(superAdminEmailId,true);
//
//            if(superAdminDetails==null)
//            {
//                log.warn(Constants.Messages.noActiveUserFound);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.Messages.noActiveUserFound);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            if(superAdminDetails.getRoleId()!=1)
//            {
//                log.warn(Constants.Messages.NOT_AUTHORIZED_TO_CREATE_EMPLOYEE);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.Messages.NOT_AUTHORIZED_TO_CREATE_EMPLOYEE);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
////            EmployeeMaste
//
//            CompanyMasters companyDetails=companyMastersRepository.findByIdAndActive(companyId,true);
//
//
//            if(companyDetails==null)
//            {
//                log.warn(Constants.Messages.COMPANY_NOT_FOUND);
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg",Constants.Messages.COMPANY_NOT_FOUND);
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//
//            EmployeeMaster companyAdminDetails=employeeMasterRepository.findByEmailIdAndActive(adminEmailId,true);
//
//            if(companyAdminDetails==null)
//            {
//                log.warn("Employee is already assigned to another company");
//                responseDTO.setMessage(Constants.Messages.badRequest);
//                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
//                respMessages.put("msg","Employee is already assigned to another company");
//                responseDTO.setResponseObj(respMessages);
//                return responseDTO;
//            }
//
//            companyAdminDetails=new EmployeeMaster();
//            companyAdminDetails.setEmployeeName(adminName);
//            companyAdminDetails.setEmailId(adminEmailId);
//            companyAdminDetails.setMobileNumber(adminMobileNumber);
//            companyAdminDetails.setPassword("Test@159357");
//            companyAdminDetails.setCreatedAt(LocalDateTime.now());
//            companyAdminDetails.setActive(true);
//            employeeMasterRepository.save(companyAdminDetails);
//
//
//
//
//
//        } catch (Exception e) {
//            log.error(Constants.Messages.error,e);
//            responseDTO.setMessage(Constants.Messages.error);
//            responseDTO.setStatusCode(Constants.StatusCode.error);
//        }
//        return responseDTO;
//    }


}
