package com.TaskManager.services;

import com.TaskManager.dtos.UserDTO;
import com.TaskManager.models.User;
import com.TaskManager.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
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
        return repository.save(newUser);
    }

    public void deleteUserById(Long id){
        repository.deleteById(id);
    }

}
