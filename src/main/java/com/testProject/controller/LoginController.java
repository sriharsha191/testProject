package com.testProject.controller;

import com.testProject.dto.AttendanceDTO;
import com.testProject.dto.ResponseDTO;
import com.testProject.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restAPI/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/authenticateUser")
    public ResponseDTO authenticateUser(HttpServletRequest request, @RequestBody String jsonData)
    {
        return loginService.authenticateUser(request, jsonData);
    }

    @PostMapping(value = "/checkInCheckOut")
    public ResponseDTO checkInOrCheckOut(HttpServletRequest request,@Valid @RequestBody AttendanceDTO attendanceDTO)
    {
        return loginService.checkInOrCheckOut(request, attendanceDTO);
    }

    @PostMapping(value = "/employeeAttendanceHistory")
    public ResponseDTO employeeAttendanceHistory(HttpServletRequest request,@RequestBody String jsonData)
    {
        return loginService.attendanceHistory(request,jsonData);
    }
}
