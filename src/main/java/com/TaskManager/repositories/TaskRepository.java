package com.TaskManager.repositories;

import com.TaskManager.models.Category;
import com.TaskManager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategory(Category category);

}
