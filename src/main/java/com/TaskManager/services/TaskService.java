package com.TaskManager.services;

import com.TaskManager.dtos.TaskDTO;
import com.TaskManager.models.Task;
import com.TaskManager.repositories.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final AuditLogService logService;

    public TaskService(TaskRepository repository, AuditLogService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    public List<Task> findAll() {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll();
        } else
            return null; // throw exception later
    }

    public Task findByTitle(String title) {
        return repository.findByTitleIgnoreCase(title).orElse(null); // throw TaskNotFoundException later
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

        logService.log("Task",
                "CREATE_TASK",
                "Created a task with title: " + data.title()
        );

        return repository.save(newTask);
    }

    // add  editTask method

    //add updateTaskState method

    public void deleteTaskById(Long id) {
        logService.log("Task",
                "DELETE_TASK",
                "Deleted the task with resource id: " + id
        );
        repository.deleteById(id);
    }

}
