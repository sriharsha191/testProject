package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENT_MASTERS")
@Data
public class PaymentMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "ACTIVE")
    private boolean active=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt =LocalDateTime.now();
}
