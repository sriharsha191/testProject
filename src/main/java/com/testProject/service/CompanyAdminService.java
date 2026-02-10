package com.testProject.service;

import com.testProject.dto.*;
import com.testProject.entity.*;
import com.testProject.repository.*;
import com.testProject.utils.Constants;
import com.testProject.utils.DateTimeUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CompanyAdminService {

    private static final Logger log= LogManager.getLogger(CompanyAdminService.class);

    @Autowired
    private EmployeeMasterRepository employeeMasterRepository;

    @Autowired
    private SitesMasterRepository sitesMasterRepository;

    @Autowired
    private CompanyMastersRepository companyMastersRepository;

    @Autowired
    private ButtonModelMastersRepository buttonModelMastersRepository;

    @Autowired
    private AMCTypeMastersRepository amcTypeMastersRepository;

    @Autowired
    private ControllerTypeMastersRepository controllerTypeMastersRepository;

    @Autowired
    private DriverTypeMastersRepository driverTypeMastersRepository;

    @Autowired
    private PaymentMastersRepository paymentMastersRepository;

    @Autowired
    private MotorTypeMasterRepository motorTypeMasterRepository;

    @Autowired
    private RoleMasterRepository roleMasterRepository;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private LanguageMastersRepository languageMastersRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    public ResponseDTO saveOrUpdateSiteDetails(HttpServletRequest request, SiteCreationDTO siteCreationDTO)
    {
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try {

            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailIdAndActive(siteCreationDTO.getEmailId(),true);

            if(employeeDetails==null)
            {
                log.warn(Constants.Messages.noActiveUserFound);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.noActiveUserFound);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(employeeDetails.getRoleId()!=2)
            {
                log.warn(Constants.Messages.NOT_AUTHORIZED_TO_CREATE_SITE);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.NOT_AUTHORIZED_TO_CREATE_SITE);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(!Objects.equals(employeeDetails.getCompanyId(), siteCreationDTO.getAssociatedCompanyId()))
            {
                log.warn(Constants.Messages.COMPANY_MISMATCH);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_MISMATCH);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            SitesMasters siteDetails=sitesMasterRepository.findByApartmentNameAndActive(siteCreationDTO.getApartmentName(),true);

            if(siteCreationDTO.getId()==0L)
            {
                if(siteDetails!=null)
                {
                    log.warn("Already Site exists with this name : {}",siteCreationDTO.getApartmentName());
                    responseDTO.setMessage(Constants.Messages.badRequest);
                    responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                    respMessages.put("msg","Already Site exists with this name :"+siteCreationDTO.getApartmentName());
                    responseDTO.setResponseObj(respMessages);
                    return responseDTO;
                }

                siteDetails=new SitesMasters();
            }


            CompanyMasters companyDetails=companyMastersRepository.findByIdAndActive(siteCreationDTO.getAssociatedCompanyId(),true);

            if(companyDetails==null)
            {
                log.warn(Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }



            siteDetails.setApartmentName(siteCreationDTO.getApartmentName());
            siteDetails.setAssociatedCompanyId(companyDetails.getId());
            siteDetails.setAddress(siteCreationDTO.getAddress());
            siteDetails.setNoOfFloors(siteCreationDTO.getNoOfFloors());
            siteDetails.setLiftModel(siteCreationDTO.getLiftModel());
            siteDetails.setPrimaryContactName(siteCreationDTO.getPrimaryContactName());
            siteDetails.setPrimaryContactMobileNumber(siteCreationDTO.getPrimaryContactMobileNumber());
            siteDetails.setSecondaryContactName(siteCreationDTO.getSecondaryContactName());
            siteDetails.setSecondaryContactMobileNumber(siteCreationDTO.getSecondaryContactMobileNumber());
            siteDetails.setTertiaryContactName(siteCreationDTO.getTertiaryContactName());
            siteDetails.setTertiaryContactMobileNumber(siteCreationDTO.getTertiaryContactMobileNumber());
            siteDetails.setLocation(siteCreationDTO.getLocation());

            AMCTypeMasters AMCTypeDetails=amcTypeMastersRepository.findByIdAndActive(siteCreationDTO.getAmcTypeId(),true);

            if(AMCTypeDetails==null)
            {
                log.warn(Constants.Messages.AMC_TYPE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.AMC_TYPE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            siteDetails.setAmcTypeId(siteCreationDTO.getAmcTypeId());

            PaymentMasters paymentType=paymentMastersRepository.findByIdAndActive(siteCreationDTO.getPaymentTypeId(),true);

            if(paymentType==null)
            {
                log.warn(Constants.Messages.PAYMENT_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.PAYMENT_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            siteDetails.setPaymentTypeId(siteCreationDTO.getPaymentTypeId());

            ButtonModelMasters buttonModelMasters=buttonModelMastersRepository.findByIdAndActive(siteCreationDTO.getButtonModelId(), true);

            if(buttonModelMasters==null)
            {
                log.warn(Constants.Messages.BUTTON_MODEL_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.BUTTON_MODEL_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            siteDetails.setButtonModelId(siteCreationDTO.getButtonModelId());
            siteDetails.setOtherButtonModel(siteCreationDTO.getOtherButtonModel());
            siteDetails.setButtonModelImage(siteCreationDTO.getButtonModelImage());

            MotorTypeMasters motorTypeMasters=motorTypeMasterRepository.findByIdAndActive(siteCreationDTO.getMotorTypeId(),true);

            if(motorTypeMasters==null)
            {
                log.warn(Constants.Messages.MOTOR_TYPE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.MOTOR_TYPE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            siteDetails.setMotorTypeId(siteCreationDTO.getMotorTypeId());
            siteDetails.setOtherMotorModel(siteCreationDTO.getOtherMotorModel());
            siteDetails.setOtherMotorModelImage(siteCreationDTO.getOtherMotorModelImage());


            DriverTypeMasters driverTypeMasters=driverTypeMastersRepository.findByIdAndActive(siteCreationDTO.getDriveTypeId(),true);

            if(driverTypeMasters==null)
            {
                log.warn(Constants.Messages.DRIVER_TYPE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.DRIVER_TYPE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            ControllerTypeMasters controllerTypeMasters =controllerTypeMastersRepository.findByIdAndActive(siteCreationDTO.getControllerTypeId(),true);

            if(controllerTypeMasters==null)
            {
                log.warn(Constants.Messages.CONTROLLER_TYPE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.CONTROLLER_TYPE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            siteDetails.setControllerTypeId(siteCreationDTO.getControllerTypeId());
            siteDetails.setControllerModelImage(siteCreationDTO.getControllerModelImage());
            siteDetails.setDriveTypeId(siteCreationDTO.getDriveTypeId());
            siteDetails.setOtherDriveType(siteCreationDTO.getOtherDriveType());
            siteDetails.setDriveTypeImage(siteCreationDTO.getDriveTypeImage());
            siteDetails.setLiftAge(siteCreationDTO.getLiftAge());
            siteDetails.setLiftMake(siteCreationDTO.getLiftMake());
            siteDetails.setOthers(siteCreationDTO.getOthers());
            siteDetails.setCreatedAt(LocalDateTime.now());
            siteDetails.setActive(true);

            sitesMasterRepository.save(siteDetails);

            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            String msg=null;

            if(siteCreationDTO.getId()==0L)
            {
                msg="Site registered successfully.";

            }
            else
            {
                msg="Site details updated successfully.";
            }

            respMessages.put("msg",msg);
            responseDTO.setResponseObj(respMessages);



        } catch (Exception e) {
            log.error(Constants.Messages.error,e);
            e.printStackTrace();
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }

    public ResponseDTO getCompanyWiseSitesList(HttpServletRequest request,String jsonData)
    {
        log.info("==> getCompanyWiseSitesList Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {
            JSONObject jsonObject=new JSONObject(jsonData);
            String adminEmailId=jsonObject.optString("adminEmailId",null);

            if(adminEmailId==null)
            {
                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_EMAIL_ID);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_EMAIL_ID);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            CompanyMasters companyMasters=companyMastersRepository.findByAdminEmailId(adminEmailId);

            if(companyMasters==null)
            {
                log.warn(Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster companyAdminDetails=employeeMasterRepository.findByEmailIdAndActive(adminEmailId,true);

            if(companyAdminDetails==null)
            {
                log.warn(Constants.Messages.COMPANY_ADMIN_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_ADMIN_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            List<SiteDetailsDTO> companyWiseSitesList=new ArrayList<>();

            List<SitesMasters> sitesMasters=sitesMasterRepository.findByAssociatedCompanyIdAndActive(companyMasters.getId(),true);

            if(!sitesMasters.isEmpty())
            {
                sitesMasters.forEach(sites -> {
                    SiteDetailsDTO siteDetailsDTO=new SiteDetailsDTO();

                    siteDetailsDTO.setId(sites.getId());
                    siteDetailsDTO.setApartmentName(sites.getApartmentName());
                    siteDetailsDTO.setAssociatedCompanyId(sites.getAssociatedCompanyId());
                    siteDetailsDTO.setAssociatedCompanyName(companyMasters.getCompanyName());
                    siteDetailsDTO.setAddress(sites.getAddress());
                    siteDetailsDTO.setNoOfFloors(sites.getNoOfFloors());
                    siteDetailsDTO.setLiftModel(sites.getLiftModel());
                    siteDetailsDTO.setPrimaryContactName(sites.getPrimaryContactName());
                    siteDetailsDTO.setPrimaryContactMobileNumber(sites.getPrimaryContactMobileNumber());
                    siteDetailsDTO.setSecondaryContactName(sites.getSecondaryContactName());
                    siteDetailsDTO.setSecondaryContactMobileNumber(sites.getSecondaryContactMobileNumber());
                    siteDetailsDTO.setTertiaryContactName(sites.getTertiaryContactName());
                    siteDetailsDTO.setTertiaryContactMobileNumber(sites.getTertiaryContactMobileNumber());
                    siteDetailsDTO.setLocation(sites.getLocation());
                    siteDetailsDTO.setAmcTypeId(sites.getAmcTypeId());

                    AMCTypeMasters amcTypeMasters=amcTypeMastersRepository.findByIdAndActive(siteDetailsDTO.getAmcTypeId(),true);
                    if(amcTypeMasters!=null)
                    {
                        siteDetailsDTO.setAmcType(amcTypeMasters.getAMCType());
                    }

                    siteDetailsDTO.setPaymentTypeId(sites.getPaymentTypeId());

                    PaymentMasters paymentMasters=paymentMastersRepository.findByIdAndActive(siteDetailsDTO.getPaymentTypeId(), true);

                    if(paymentMasters!=null)
                    {
                        siteDetailsDTO.setPaymentType(paymentMasters.getPaymentType());
                    }

                    siteDetailsDTO.setButtonModelId(sites.getButtonModelId());

                    ButtonModelMasters buttonModelMasters=buttonModelMastersRepository.findByIdAndActive(siteDetailsDTO.getButtonModelId(),true);

                    if(buttonModelMasters!=null)
                    {
                        siteDetailsDTO.setButtonModel(buttonModelMasters.getButtonModel());
                    }

                    siteDetailsDTO.setOtherButtonModel(sites.getOtherButtonModel());

                    siteDetailsDTO.setButtonModelImage(sites.getButtonModelImage());

                    siteDetailsDTO.setMotorTypeId(sites.getMotorTypeId());


                    MotorTypeMasters motorTypeMasters=motorTypeMasterRepository.findByIdAndActive(sites.getMotorTypeId(), true);

                    if(motorTypeMasters!=null)
                    {
                        siteDetailsDTO.setMotorType(motorTypeMasters.getMotorType());
                    }

                    siteDetailsDTO.setOtherMotorModel(sites.getOtherMotorModel());
                    siteDetailsDTO.setControllerTypeId(sites.getControllerTypeId());

                    ControllerTypeMasters controllerTypeMasters=controllerTypeMastersRepository.findByIdAndActive(sites.getControllerTypeId(),true);

                    if(controllerTypeMasters!=null)
                    {
                        siteDetailsDTO.setControllerType(controllerTypeMasters.getControllerType());
                    }
                    siteDetailsDTO.setOtherControllerType(sites.getOtherControllerType());
                    siteDetailsDTO.setControllerModelImage(sites.getControllerModelImage());


                    siteDetailsDTO.setDriveTypeId(sites.getDriveTypeId());

                    DriverTypeMasters driverTypeMasters=driverTypeMastersRepository.findByIdAndActive(sites.getDriveTypeId(),true);

                    if(driverTypeMasters!=null)
                    {
                        siteDetailsDTO.setDriveType(driverTypeMasters.getDriverType());
                    }

                    siteDetailsDTO.setDriveTypeImage(sites.getDriveTypeImage());
                    siteDetailsDTO.setLiftAge(sites.getLiftAge());
                    siteDetailsDTO.setLiftMake(sites.getLiftMake());
                    siteDetailsDTO.setOthers(sites.getOthers());


                    companyWiseSitesList.add(siteDetailsDTO);
                });
            }


            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("data",companyWiseSitesList);
            responseDTO.setResponseObj(respMessages);


        }
        catch (Exception e) {
            log.error(Constants.Messages.error,e);
            e.printStackTrace();
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }


    public ResponseDTO addEmployee(HttpServletRequest request, EmployeeCreationDTO employeeCreationDTO)
    {
        log.info("==> addEmployee Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {
            CompanyMasters companyMasters=companyMastersRepository.findByAdminEmailId(employeeCreationDTO.getAdminEmailId());

            if(companyMasters==null)
            {
                log.warn(Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster companyAdminDetails=employeeMasterRepository.findByEmailIdAndActive(employeeCreationDTO.getAdminEmailId(),true);

            if(companyAdminDetails==null)
            {
                log.warn(Constants.Messages.COMPANY_ADMIN_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_ADMIN_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailIdAndActive(employeeCreationDTO.getEmailId(),true);

            if(employeeDetails!=null)
            {
                log.warn("Employee Found with this Email Id");
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setResponseObj(Constants.StatusCode.badRequest);
                respMessages.put("msg","Employee Found with this Email Id");
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            employeeDetails=new EmployeeMaster();
            employeeDetails.setEmployeeName(employeeCreationDTO.getEmployeeName());
            employeeDetails.setCompanyId(companyMasters.getId());
            employeeDetails.setRoleId(3L);
            employeeDetails.setEmailId(employeeCreationDTO.getEmailId());
            employeeDetails.setMobileNumber(employeeCreationDTO.getMobileNumber());
            employeeDetails.setAlternativeMobileNumber(employeeCreationDTO.getAlternativeMobileNumber());
            employeeDetails.setEmployeeProfileImage(employeeCreationDTO.getEmployeeProfileImage());
            employeeDetails.setDateOfBirth(employeeCreationDTO.getDateOfBirth());
            employeeDetails.setPrimaryFamilyMemberName(employeeCreationDTO.getPrimaryFamilyMemberName());
            employeeDetails.setPrimaryFamilyMemberMobileNumber(employeeCreationDTO.getPrimaryFamilyMemberMobileNumber());
            employeeDetails.setSecondaryFamilyMemberName(employeeCreationDTO.getSecondaryFamilyMemberName());
            employeeDetails.setSecondaryFamilyMemberMobileNumber(employeeCreationDTO.getSecondaryFamilyMemberMobileNumber());
            employeeDetails.setDateOfJoining(employeeCreationDTO.getDateOfJoining());
            employeeDetails.setEmployeeType(employeeCreationDTO.getEmployeeType());
            employeeDetails.setPermanentAddress(employeeCreationDTO.getPermanentAddress());
            employeeDetails.setCurrentAddress(employeeCreationDTO.getCurrentAddress());
            employeeDetails.setAadharCardImageUrl(employeeCreationDTO.getAadharCardImageUrl());
            employeeDetails.setBankAccountNumber(employeeCreationDTO.getBankAccountNumber());
            employeeDetails.setUpiMobileNumber(employeeCreationDTO.getUpiMobileNumber());
            employeeDetails.setSpokenLanguages(employeeCreationDTO.getSpokenLanguages());
            employeeDetails.setBloodGroup(employeeCreationDTO.getBloodGroup());


            employeeDetails.setPassword(employeeCreationDTO.getEmployeeName()+"@159357");
            employeeDetails.setCreatedAt(LocalDateTime.now());
            employeeDetails.setActive(true);
            employeeMasterRepository.save(employeeDetails);



            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("msg",Constants.Messages.EMPLOYEE_REGISTERED_SUCCESSFULLY);
            responseDTO.setResponseObj(respMessages);

        }
        catch (Exception e)
        {
            log.error(Constants.Messages.error,e);
            e.printStackTrace();
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }


    public ResponseDTO companyWiseEmployeeList(HttpServletRequest request,String jsonData)
    {
        log.info("==> companyWiseEmployeeList Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {
            JSONObject jsonObject=new JSONObject(jsonData);
            String emailId=jsonObject.optString("emailId",null);

            if(emailId==null)
            {
                log.warn(Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_EMAIL_ID);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.ValidationMessages.NULL_OR_EMPTY_ADMIN_EMAIL_ID);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            CompanyMasters companyMasters=companyMastersRepository.findByAdminEmailId(emailId);

            if(companyMasters==null)
            {
                log.warn(Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster companyAdminDetails=employeeMasterRepository.findByEmailIdAndActive(emailId,true);

            if(companyAdminDetails==null)
            {
                log.warn(Constants.Messages.COMPANY_ADMIN_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_ADMIN_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            List<EmployeeDetailsDTO> companyWiseEmployees=new ArrayList<>();

            List<EmployeeMaster> employeeList=employeeMasterRepository.findByCompanyIdAndActive(companyAdminDetails.getCompanyId(),true);

            if(!employeeList.isEmpty())
            {
                employeeList.stream().filter(value->value.getRoleId()==3L).forEach(employee->{
                    EmployeeDetailsDTO employeeDetailsDTO=new EmployeeDetailsDTO();
                    employeeDetailsDTO.setEmployeeId(employee.getId());
                    employeeDetailsDTO.setEmailId(employee.getEmailId());
                    employeeDetailsDTO.setEmployeeName(employee.getEmployeeName());
                    employeeDetailsDTO.setCompanyId(employee.getCompanyId());
                    employeeDetailsDTO.setCompanyName(companyMasters.getCompanyName());
                    employeeDetailsDTO.setRoleId(employee.getRoleId());

                    RoleMaster roleMaster=roleMasterRepository.findByIdAndActive((long)employee.getRoleId(),true);

                    if(roleMaster!=null)
                    {
                        employeeDetailsDTO.setRole(roleMaster.getRoleName());
                    }

                    employeeDetailsDTO.setMobileNumber(employee.getMobileNumber());

                    employeeDetailsDTO.setAlternativeMobileNumber(employee.getAlternativeMobileNumber());
                    employeeDetailsDTO.setEmployeeProfileImageURL(employee.getEmployeeProfileImage());
                    employeeDetailsDTO.setDateOfBirth(employee.getDateOfBirth());
                    employeeDetailsDTO.setPrimaryFamilyMemberName(employee.getPrimaryFamilyMemberName());
                    employeeDetailsDTO.setPrimaryFamilyMemberMobileNumber(employee.getPrimaryFamilyMemberMobileNumber());
                    employeeDetailsDTO.setSecondaryFamilyMemberName(employee.getSecondaryFamilyMemberName());
                    employeeDetailsDTO.setSecondaryFamilyMemberMobileNumber(employee.getSecondaryFamilyMemberMobileNumber());
                    employeeDetailsDTO.setDateOfJoining(dateTimeUtil.dateFormatter(employee.getDateOfJoining()));
                    employeeDetailsDTO.setEmployeeTypeId(employee.getEmployeeType());
                    employeeDetailsDTO.setEmployeeType(null);
                    employeeDetailsDTO.setPermanentAddress(employee.getPermanentAddress());
                    employeeDetailsDTO.setCurrentAddress(employee.getCurrentAddress());
                    employeeDetailsDTO.setAadharCardImageUrl(employee.getAadharCardImageUrl());
                    employeeDetailsDTO.setBankAccountNumber(employee.getBankAccountNumber());
                    employeeDetailsDTO.setUpiMobileNumber(employee.getUpiMobileNumber());

                    Map<Long,String> languageList=new HashMap<>();

                    if(!employee.getSpokenLanguages().isEmpty())
                    {
                        employee.getSpokenLanguages().forEach(lan->{

                            LanguageMasters language=languageMastersRepository.findById((long)lan);

                            if(language!=null)
                            {
                                languageList.put(language.getId(),language.getLanguage());
                            }
                        });

                    }

                    employeeDetailsDTO.setSpokenLanguages(languageList);

                    companyWiseEmployees.add(employeeDetailsDTO);

                });

            }

            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("data",companyWiseEmployees);
            responseDTO.setResponseObj(respMessages);

        }
        catch (Exception e)
        {
            log.error(Constants.Messages.error,e);
            e.printStackTrace();
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }


    public ResponseDTO createWorkOrder(HttpServletRequest request,WorkOrderCreationDTO workOrderCreationDTO)
    {
        log.info("==> createWorkOrder Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {
            EmployeeMaster adminDetails=employeeMasterRepository.findByEmailId(workOrderCreationDTO.getEmailId());

            if(adminDetails==null)
            {
                log.warn(Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            WorkOrder workOrder;

            if(workOrderCreationDTO.getId()==0L)
            {
                workOrder=new WorkOrder();
            }
            else
            {
                workOrder=workOrderRepository.findById((long)workOrderCreationDTO.getId());

                if(workOrder==null)
                {
                    log.warn(Constants.Messages.WORK_ORDER_NOT_FOUND);
                    responseDTO.setMessage(Constants.Messages.badRequest);
                    responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                    respMessages.put("msg",Constants.Messages.WORK_ORDER_NOT_FOUND);
                    responseDTO.setResponseObj(respMessages);
                    return responseDTO;
                }

            }

            SitesMasters sitesMasters=sitesMasterRepository.findById((long)workOrderCreationDTO.getApartmentId());

            if(sitesMasters==null)
            {
                log.warn(Constants.Messages.SITE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.SITE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }



            workOrder.setApartmentId(workOrder.getApartmentId());
            workOrder.setComplaintBy(workOrderCreationDTO.getComplaintBy());
            workOrder.setMobileNumber(workOrderCreationDTO.getMobileNumber());
            workOrder.setWorkTypeId(workOrder.getWorkTypeId());
            workOrder.setOthers(workOrderCreationDTO.getOthers());

            if(!workOrderCreationDTO.getSupportEmployeeIds().isEmpty())
            {
                for(Long empIds: workOrderCreationDTO.getSupportEmployeeIds())
                {
                    EmployeeMaster supportEmpDetails=employeeMasterRepository.findById((long)empIds);

                    if(supportEmpDetails==null)
                    {
                        log.warn(Constants.Messages.SUPPORT_EMPLOYEE_NOT_FOUND);
                        responseDTO.setMessage(Constants.Messages.badRequest);
                        responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                        respMessages.put("msg",Constants.Messages.SUPPORT_EMPLOYEE_NOT_FOUND);
                        responseDTO.setResponseObj(respMessages);
                        return responseDTO;
                    }
                }

                workOrder.setSupportEmployees(workOrderCreationDTO.getSupportEmployeeIds());

            }

            workOrder.setWorkCreatedBy(workOrderCreationDTO.getWorkCreatedBy());
            workOrder.setWorkAssignedDate(workOrderCreationDTO.getWorkAssignedDate());
            workOrder.setStatus(true);
            workOrder.setCreatedAt(LocalDateTime.now());
            workOrderRepository.save(workOrder);


        }
        catch (Exception e)
        {
            log.error(Constants.Messages.error,e);
            e.printStackTrace();
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }
}
