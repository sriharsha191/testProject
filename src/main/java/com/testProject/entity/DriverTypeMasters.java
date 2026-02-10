package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "DRIVER_TYPE_MASTERS")
@Data
public class DriverTypeMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DRIVER_TYPE")
    private String driverType;

    @Column(name = "ACTIVE")
    private boolean active=true;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn=LocalDateTime.now();
}
