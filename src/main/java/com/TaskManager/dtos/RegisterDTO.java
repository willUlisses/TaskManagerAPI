package com.TaskManager.dtos;

import com.TaskManager.models.enumerations.UserRoles;

public record RegisterDTO(String username, String password, String email, UserRoles role) {
}
