package com.todo.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.api.entities.DTOS.TaskCreateDto;
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

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskCreateDto obj){
        TaskResponseDto task = taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).body(task);
    }
    
}
