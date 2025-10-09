package com.TaskManager.models.enumerations;

import lombok.Getter;

@Getter
public enum UserRoles {
    USER("user"),
    ADMIN("admin");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }
}
