package com.testProject.controller;

import com.testProject.dto.ResponseDTO;
import com.testProject.service.DashboardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restAPI/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping(value = "/getMasterData")
    public ResponseDTO getMasterList(HttpServletRequest request, @RequestBody String jsonData)
    {
        return dashboardService.getMasterData(request, jsonData);
    }
}
