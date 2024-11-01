package com.todo.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.api.entities.Task;
import com.todo.api.entities.DTOS.TaskCreateDto;
import com.todo.api.entities.DTOS.TaskResponseDto;
import com.todo.api.exceptions.ObjectNotFoundException;
import com.todo.api.repositories.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDto> findAll(){
        return taskRepository.findAll()
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());        
    }

    public TaskResponseDto findById(Long id){
        var task = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Task não encontrada"));
        return new TaskResponseDto(task);
    }

    public TaskResponseDto create(TaskCreateDto obj){
        Task task = new Task(obj);
        taskRepository.save(task);
        return new TaskResponseDto(task);        
    }    
}
