package com.todo.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.api.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

  Page<Task> findByUserId(Long userId, Pageable pageable);
    
}
