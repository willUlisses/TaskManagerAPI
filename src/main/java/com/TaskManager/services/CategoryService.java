package com.TaskManager.services;

import com.TaskManager.dtos.CategoryDTO;
import com.TaskManager.models.Category;
import com.TaskManager.repositories.CategoryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final AuditLogService logService;


    public CategoryService(CategoryRepository repository, AuditLogService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    public List<Category> findAll() {
        List<Category> categories = repository.findAll();

        if (!categories.isEmpty()){
            return categories;
        } else
            return null;
    }

    public Category findCategoryById(Long id) {
        return repository.findById(id).orElse(null); // throw CategoryNotFoundException
    }

    public Category findCategoryByName(String categoryName) {
        return repository.findByName(categoryName).orElse(null); // throw CategoryNotFoundException
    }

    public Category createCategory(CategoryDTO data) {
        Category newCategory =  new Category(data.name(), data.description());

        logService.log("Category",
                "CREATE_CATEGORY",
                "Created a new category with name: " + data.name()
        );

        return repository.save(newCategory);
    }

    public void deleteById(Long id) {
        logService.log("Category",
                "DELETE_CATEGORY",
                "Deleted a category with resource id: " + id
        );

        repository.deleteById(id);
    }

}
