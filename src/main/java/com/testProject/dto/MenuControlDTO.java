package com.testProject.dto;

import lombok.Data;

@Data
public class MenuControlDTO {

    private Long id;

    private String menuItem;

    private boolean canView;

    private boolean active;
}
