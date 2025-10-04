package com.TaskManager.services;

import com.TaskManager.dtos.TaskDTO;
import com.TaskManager.models.Task;
import com.TaskManager.models.enumerations.TaskState;
import com.TaskManager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll();
        } else
            return null; // throw exception later
    }

    public Task findByTitle(String title) {
        return repository.findByTitle(title).orElse(null); // throw TaskNotFoundException later
    }

    public Task findTaskById(Long id) {
        return repository.findById(id).orElse(null); // throw IllegalStatementException later
    }

    public List<Task> findByCategoryNameIgnoreCase(String categoryName) {
        return repository.findByCategoryNameIgnoreCase(categoryName);
    }

    public List<Task> findByCategoryId(Long id) {
        return repository.findByCategoryId(id);
    }

    public Task createTask(TaskDTO data) {
        Task newTask = new Task(data.title(), data.description());
        return repository.save(newTask);
    }

    // add editTask method

    //add updateTaskState method

    public void deleteTaskById(Long id) {
        repository.deleteById(id);
    }

}
