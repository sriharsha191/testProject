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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    private static final Logger log= LogManager.getLogger(LoginService.class);

    @Autowired
    private EmployeeMasterRepository employeeMasterRepository;

    @Autowired
    private RoleMasterRepository roleMasterRepository;

    @Autowired
    private CompanyMastersRepository companyMastersRepository;

    @Autowired
    private RoleMenuMappingRepository roleMenuMappingRepository;

    @Autowired
    private MenuMasterRepository menuMasterRepository;

    @Autowired
    private EmployeeAttendanceHistoryRepository employeeAttendanceHistoryRepository;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public ResponseDTO authenticateUser(HttpServletRequest request, String jsonData)
    {
        log.info("==> authenticateUser Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {

            JSONObject jsonObject=new JSONObject(jsonData);
            String emailId=jsonObject.optString("emailId",null);
            String password=jsonObject.optString("password",null);


            if(emailId==null)
            {
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.emptyEmailId);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(password==null)
            {
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.emptyPassword);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            log.info("Email Id : {}",emailId);
            log.info("Password : {}",password);

            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailIdAndActive(emailId,true);

            if(employeeDetails==null)
            {
                log.info(Constants.Messages.noActiveUserFound);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.noActiveUserFound);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(!password.equals(employeeDetails.getPassword()))
            {
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.invalidPassword);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeDetailsDTO employeeDetailsDTO=getEmployeeDetails(employeeDetails);

            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("data",employeeDetailsDTO);
            responseDTO.setResponseObj(respMessages);


        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return responseDTO;
    }

    private EmployeeDetailsDTO getEmployeeDetails(EmployeeMaster employeeDetails)
    {
        log.info("==> getEmployeeDetails Method");

        EmployeeDetailsDTO employeeDetailsDTO=new EmployeeDetailsDTO();
        employeeDetailsDTO.setEmployeeId(employeeDetails.getId());
        employeeDetailsDTO.setEmailId(employeeDetails.getEmailId());
        employeeDetailsDTO.setEmployeeName(employeeDetails.getEmployeeName());
        employeeDetailsDTO.setMobileNumber(employeeDetails.getMobileNumber());
        employeeDetailsDTO.setRoleId(employeeDetails.getRoleId());

        RoleMaster roleDetails=roleMasterRepository.findByIdAndActive(employeeDetails.getRoleId(),true);
        if(roleDetails!=null)
        {
            employeeDetailsDTO.setRole(roleDetails.getRoleName());
            List<RoleMenuMapping> roleMenuMapping=roleMenuMappingRepository.findByRoleIdAndActiveAndCanView(roleDetails.getId(),true,true);

            List<MenuControlDTO> menuControlList=new ArrayList<>();
            if(!roleMenuMapping.isEmpty())
            {
                roleMenuMapping.forEach(control->{
                    MenuControlDTO menuControlDTO=new MenuControlDTO();
                    menuControlDTO.setId(control.getId());

                    MenuMaster menuMaster=menuMasterRepository.findByIdAndActive((long)control.getMenuId(),true);

                    if(menuMaster!=null)
                    {
                        menuControlDTO.setMenuItem(menuMaster.getMenuItem());
                    }
                    menuControlDTO.setCanView(control.isCanView());
                    menuControlDTO.setActive(control.isActive());

                    menuControlList.add(menuControlDTO);
                });


                employeeDetailsDTO.setMenuControl(menuControlList);
            }
        }

        employeeDetailsDTO.setCompanyId(employeeDetails.getCompanyId());

        if(employeeDetails.getCompanyId()!=null)
        {
            CompanyMasters companyMastersDetails = companyMastersRepository.findByIdAndActive( employeeDetails.getCompanyId(),true);
            if(companyMastersDetails!=null)
            {
                employeeDetailsDTO.setCompanyName(companyMastersDetails.getCompanyName());
            }
        }



        return employeeDetailsDTO;
    }

    public ResponseDTO checkInOrCheckOut(HttpServletRequest request, AttendanceDTO attendanceDTO)
    {
        log.info("==> checkInOrCheckOut Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try
        {
            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailId(attendanceDTO.getEmailId());

            if(employeeDetails==null)
            {
                log.warn(Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(attendanceDTO.getFlag()==0)
            {
                log.warn(Constants.Messages.INVALID_FLAG);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.INVALID_FLAG);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(attendanceDTO.getFlag()==1 && attendanceDTO.getCheckInTime()==null)
            {
                log.warn(Constants.Messages.NULL_OR_EMPTY_CHECK_IN_TIME);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.NULL_OR_EMPTY_CHECK_IN_TIME);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(attendanceDTO.getFlag()==2 && attendanceDTO.getCheckOutTime()==null)
            {
                log.warn(Constants.Messages.NULL_OR_EMPTY_CHECK_OUT_TIME);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.NULL_OR_EMPTY_CHECK_OUT_TIME);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(attendanceDTO.getFlag()==1)
            {
                List<EmployeeAttendanceHistory> history=employeeAttendanceHistoryRepository.getEmployeeAttendanceHistory(attendanceDTO.getEmailId(),LocalDate.now(), true);

                if(history!=null && !history.isEmpty())
                {
                    if(history.get(0).getCheckOutTime()==null)
                    {
                        log.warn(Constants.ValidationMessages.CHECK_IN_EXISTS);
                        responseDTO.setMessage(Constants.Messages.badRequest);
                        responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                        respMessages.put("msg",Constants.ValidationMessages.CHECK_IN_EXISTS);
                        responseDTO.setResponseObj(respMessages);
                        return responseDTO;
                    }
                }

                EmployeeAttendanceHistory employeeAttendanceHistory=new EmployeeAttendanceHistory();
                employeeAttendanceHistory.setAttendanceDate(LocalDate.now());
                employeeAttendanceHistory.setEmployeeEmailId(attendanceDTO.getEmailId());
                employeeAttendanceHistory.setCheckInTime(attendanceDTO.getCheckInTime());
                employeeAttendanceHistory.setCheckOutTime(attendanceDTO.getCheckOutTime());
                employeeAttendanceHistory.setCheckInLatitude(attendanceDTO.getLatitude());
                employeeAttendanceHistory.setCheckInLongitude(attendanceDTO.getLongitude());
                employeeAttendanceHistoryRepository.save(employeeAttendanceHistory);
            }

            if(attendanceDTO.getFlag()==2)
            {
                List<EmployeeAttendanceHistory> history=employeeAttendanceHistoryRepository.getEmployeeAttendanceHistory(attendanceDTO.getEmailId(),LocalDate.now(), true);

                if(history==null || history.get(0).getCheckInTime()==null)
                {
                    log.warn(Constants.ValidationMessages.CHECK_IN_DOES_NOT_EXISTS);
                    responseDTO.setMessage(Constants.Messages.badRequest);
                    responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                    respMessages.put("msg",Constants.ValidationMessages.CHECK_IN_DOES_NOT_EXISTS);
                    responseDTO.setResponseObj(respMessages);
                    return responseDTO;
                }
                else
                {
                    if(history.get(0).getCheckOutTime()==null)
                    {
                        history.get(0).setCheckOutTime(attendanceDTO.getCheckOutTime());
                        history.get(0).setModifiedOn(LocalDateTime.now());
                        history.get(0).setCheckOutLatitude(attendanceDTO.getLatitude());
                        history.get(0).setCheckOutLongitude(attendanceDTO.getLongitude());
                        history.get(0).setModifiedOn(LocalDateTime.now());
                        employeeAttendanceHistoryRepository.save(history.get(0));
                    }
                    else
                    {
                        log.warn(Constants.ValidationMessages.CHECK_OUT_EXISTS);
                        responseDTO.setMessage(Constants.Messages.badRequest);
                        responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                        respMessages.put("msg",Constants.ValidationMessages.CHECK_OUT_EXISTS);
                        responseDTO.setResponseObj(respMessages);
                        return responseDTO;
                    }
                }
            }





            String msg=null;

            if(attendanceDTO.getFlag()==1)
            {
                msg="Check In Successful";
            }
            else if(attendanceDTO.getFlag()==2)
            {
                msg="Check Out Successful";
            }

            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("msg",msg);
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

    public ResponseDTO attendanceHistory(HttpServletRequest request,String jsonData)
    {
        log.info("==> attendanceHistory Method");
        ResponseDTO responseDTO=new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try {

            JSONObject jsonObject=new JSONObject(jsonData);
            String emailId=jsonObject.optString("emailId",null);

            if(emailId==null)
            {
                log.info(Constants.Messages.emptyEmailId);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.emptyEmailId);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster employeeDetails=employeeMasterRepository.findByEmailId(emailId);

            if(employeeDetails==null)
            {
                log.info(Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            List<AttendanceHistoryDTO> attendanceHistory=new ArrayList<>();

            List<EmployeeAttendanceHistory> attendanceList=employeeAttendanceHistoryRepository.findByEmployeeEmailIdAndStatus(emailId,true);


            if(!attendanceList.isEmpty())
            {
                attendanceHistory.addAll(attendanceList.stream().map(history->{
                    AttendanceHistoryDTO dto=new AttendanceHistoryDTO();
                    dto.setEmailId(emailId);
                    dto.setEmployeeName(employeeDetails.getEmployeeName());
                    dto.setAttendanceDate(history.getAttendanceDate());
                    dto.setFormattedAttendanceDate(dateTimeUtil.dateFormatter(history.getAttendanceDate()));
                    dto.setCheckInTime(history.getCheckInTime());
                    dto.setFormattedCheckInTime(dateTimeUtil.timeStampFormatter(history.getCheckInTime()));
                    dto.setCheckOutTime(history.getCheckOutTime());
                    dto.setFormattedCheckOutTime(dateTimeUtil.timeStampFormatter(history.getCheckOutTime()));
                    dto.setCheckInLatitude(history.getCheckInLatitude());
                    dto.setCheckInLongitude(history.getCheckInLongitude());
                    dto.setCheckOutLatitude(history.getCheckOutLatitude());
                    dto.setCheckOutLongitude(history.getCheckOutLongitude());
                    return dto;
                }).toList());
            }


            log.info(Constants.Messages.success);
            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("data",attendanceHistory);
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
}
