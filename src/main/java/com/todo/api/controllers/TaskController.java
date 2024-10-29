package com.todo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.entities.DTOS.TaskResponseDto;
import com.todo.api.services.TaskService;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findAll(){
        List<TaskResponseDto> tasks = taskService.findAll();
        return ResponseEntity.ok().body(tasks);
    }
    
}
