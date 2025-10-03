package com.TaskManager.dtos;

import com.TaskManager.models.enumerations.UserRoles;

public record UserDTO(String username, String password, String email, UserRoles role) {
}
