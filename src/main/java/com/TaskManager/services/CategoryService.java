package com.TaskManager.services;

import com.TaskManager.dtos.CategoryDTO;
import com.TaskManager.models.Category;
import com.TaskManager.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
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
        return repository.save(newCategory);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
