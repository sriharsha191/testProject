package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "MOTOR_TYPE_MASTERS")
@Data
public class MotorTypeMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MOTOR_TYPE")
    private String motorType;

    @Column(name = "ACTIVE")
    private boolean active=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt =LocalDateTime.now();
}
