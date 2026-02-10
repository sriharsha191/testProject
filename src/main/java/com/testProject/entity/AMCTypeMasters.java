package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "AMC_TYPE_MASTERS")
@Data
public class AMCTypeMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMC_TYPE")
    private String AMCType;

    @Column(name = "ACTIVE")
    private boolean active=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdOn=LocalDateTime.now();
}
