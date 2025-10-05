package com.TaskManager.services;

import com.TaskManager.dtos.UserDTO;
import com.TaskManager.models.User;
import com.TaskManager.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final AuditLogService logService;

    public UserService(UserRepository repository, AuditLogService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    public List<User> findAll() {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll();
        } else
            return null; // throw exception later
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElse(null); // throw UserNotFoundException later
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data.username(), data.password(), data.email(), data.role()); // do password validations later

        logService.log("User",
                "CREATE_USER",
                "Created a new user with username: " + data.username() + " and email: " + data.email()
        );

        return repository.save(newUser);
    }

    public void deleteUserById(Long id){
        logService.log("User",
                "DELETE_USER",
                "Deleted a user with resource id: " + id
        );

        repository.deleteById(id);
    }

}
