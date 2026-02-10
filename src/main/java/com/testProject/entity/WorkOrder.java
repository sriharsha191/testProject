package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WORK_ORDER")
@Data
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APARTMENT_ID")
    private Long apartmentId;

    @Column(name = "COMPLAINT_BY")
    private String complaintBy;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "WORK_TYPE_ID")
    private Long workTypeId;

    @Column(name = "OTHERS")
    private String others;

    @Column(name = "SUPPORT_EMPLOYEES")
    private List<Long> supportEmployees;

    @Column(name = "WORK_CREATED_BY")
    private String workCreatedBy;

    @Column(name = "WORK_ASSIGNED_DATE")
    private LocalDate workAssignedDate;

    @Column(name = "STATUS")
    private boolean status=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();

}
