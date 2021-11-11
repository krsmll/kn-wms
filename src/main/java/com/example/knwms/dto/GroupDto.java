package com.example.knwms.dto;

import com.example.knwms.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupDto {
    private Long id;
    private String name;
    private boolean isActive;

    // UI/RF menus???

    public GroupDto(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.isActive = group.isActive();
    }
}
