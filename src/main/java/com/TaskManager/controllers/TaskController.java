package com.TaskManager.controllers;

import com.TaskManager.dtos.TaskDTO;
import com.TaskManager.models.Task;
import com.TaskManager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findTaskById(id));
    }

    @GetMapping("/find/title") // URL: /tasks/find/title?title= fazer tal coisa
    public ResponseEntity<Task> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Task> postTask(@RequestBody TaskDTO data){
        return new ResponseEntity<>(service.createTask(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long id){
        service.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
