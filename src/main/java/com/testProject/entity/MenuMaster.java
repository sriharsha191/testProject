package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "MENU_MASTER")
@Data
public class MenuMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MENU_ITEM")
    private String menuItem;

    @Column(name = "ACTIVE")
    private boolean active=false;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();
}
