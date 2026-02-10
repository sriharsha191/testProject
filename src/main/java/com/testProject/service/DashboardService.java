package com.testProject.service;

import com.testProject.dto.DropDownOptionsDTO;
import com.testProject.dto.ResponseDTO;
import com.testProject.entity.*;
import com.testProject.repository.*;
import com.testProject.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private static final Logger log= LogManager.getLogger(DashboardService.class);

    @Autowired
    private EmployeeMasterRepository employeeMasterRepository;

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
    private SitesMasterRepository sitesMasterRepository;

    public ResponseDTO getMasterData(HttpServletRequest request,String jsonData)
    {
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> masterData=new HashMap<>();
        try {

            JSONObject jsonObject=new JSONObject(jsonData);
            String emailId=jsonObject.optString("emailId",null);

            if(emailId==null)
            {
                log.warn(Constants.Messages.emptyEmailId);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                masterData.put("msg",Constants.Messages.emptyEmailId);
                responseDTO.setResponseObj(masterData);
                return responseDTO;
            }


            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailIdAndActive(emailId,true);

            if(employeeDetails==null)
            {
                log.warn(Constants.Messages.noActiveUserFound);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                masterData.put("msg",Constants.Messages.noActiveUserFound);
                responseDTO.setResponseObj(masterData);
                return responseDTO;
            }


            // Masters related to Super Admin
            if(employeeDetails.getRoleId()==1)
            {
                log.info("Super Admin");

                List<DropDownOptionsDTO> companyMasters=new ArrayList<>();

                List<CompanyMasters> companyMastersList=companyMastersRepository.findByActive(true);

                if(!companyMastersList.isEmpty())
                {
                    companyMastersList.forEach(company->{

                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(company.getId());
                        dropDownOptionsDTO.setName(company.getCompanyName());
                        companyMasters.add(dropDownOptionsDTO);
                    });

                    masterData.put("companiesList",companyMasters);
                }


            }
            else if(employeeDetails.getRoleId()==2) // Masters related to Admin
            {
                log.info("Company Wise Admin");
                List<DropDownOptionsDTO> amcTypeMasters=new ArrayList<>();
                List<DropDownOptionsDTO> buttonModelMasters=new ArrayList<>();
                List<DropDownOptionsDTO> controllerTypeMasters=new ArrayList<>();
                List<DropDownOptionsDTO> driverTypeMasters=new ArrayList<>();
                List<DropDownOptionsDTO> motorTypeMasters=new ArrayList<>();
                List<DropDownOptionsDTO> paymentTypeMasters=new ArrayList<>();
                List<DropDownOptionsDTO> sitesMasters=new ArrayList<>();


                List<AMCTypeMasters> amcTypeList=amcTypeMastersRepository.findByActive(true);

                if(!amcTypeList.isEmpty())
                {
                    amcTypeList.forEach(amcTypes->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(amcTypes.getId());
                        dropDownOptionsDTO.setName(amcTypes.getAMCType());
                        amcTypeMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("AMCTypeMasters",amcTypeMasters);

                List<ButtonModelMasters> buttonModelList=buttonModelMastersRepository.findByActive(true);

                if(!buttonModelList.isEmpty())
                {
                    buttonModelList.forEach(buttonModel->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(buttonModel.getId());
                        dropDownOptionsDTO.setName(buttonModel.getButtonModel());
                        buttonModelMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("buttonModelMasters",buttonModelMasters);

                List<ControllerTypeMasters> controllerTypeList=controllerTypeMastersRepository.findByActive(true);

                if(!controllerTypeList.isEmpty())
                {
                    controllerTypeList.forEach(controllerType->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(controllerType.getId());
                        dropDownOptionsDTO.setName(controllerType.getControllerType());
                        controllerTypeMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("controllerTypeMasters",controllerTypeMasters);

                List<DriverTypeMasters> driverTypeList=driverTypeMastersRepository.findByActive(true);

                if(!driverTypeList.isEmpty())
                {
                    driverTypeList.forEach(driveType->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(driveType.getId());
                        dropDownOptionsDTO.setName(driveType.getDriverType());
                        driverTypeMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("driveTypeMasters",driverTypeMasters);

                List<MotorTypeMasters> motorTypeList=motorTypeMasterRepository.findByActive(true);


                if(!motorTypeList.isEmpty())
                {
                    motorTypeList.forEach(motorType->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(motorType.getId());
                        dropDownOptionsDTO.setName(motorType.getMotorType());
                        motorTypeMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("motorTypeMasters",motorTypeMasters);

                List<PaymentMasters> paymentTypeList=paymentMastersRepository.findByActive(true);


                if(!paymentTypeList.isEmpty())
                {
                    paymentTypeList.forEach(motorType->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(motorType.getId());
                        dropDownOptionsDTO.setName(motorType.getPaymentType());
                        paymentTypeMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("paymentTypeMasters",paymentTypeMasters);

                List<SitesMasters> sitesList=sitesMasterRepository.findByActive(true);


                if(!sitesList.isEmpty())
                {
                    sitesList.forEach(motorType->{
                        DropDownOptionsDTO dropDownOptionsDTO=new DropDownOptionsDTO();
                        dropDownOptionsDTO.setCode(motorType.getId());
                        dropDownOptionsDTO.setName(motorType.getApartmentName());
                        sitesMasters.add(dropDownOptionsDTO);
                    });
                }

                masterData.put("sitesMaster",sitesMasters);


            }
            else if(employeeDetails.getRoleId()==3) // Masters related to Employees
            {
                log.info("Employee");
            }


            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            responseDTO.setResponseObj(masterData);




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
