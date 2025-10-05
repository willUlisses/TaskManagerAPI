package com.TaskManager.controllers;

import com.TaskManager.dtos.CategoryDTO;
import com.TaskManager.models.Category;
import com.TaskManager.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findCategoryById(id));
    }

    @GetMapping("/find/name")
    public ResponseEntity<Category> findByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findCategoryByName(name));
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO data) {
        return new ResponseEntity<>(service.createCategory(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
