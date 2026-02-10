package com.testProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "LANGUAGE_MASTERS")
@Data
public class LanguageMasters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "STATUS")
    private boolean status=true;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt=LocalDateTime.now();

}
