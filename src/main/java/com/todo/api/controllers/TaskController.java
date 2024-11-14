package com.todo.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.api.entities.DTOS.TaskCreateDto;
import com.todo.api.entities.DTOS.TaskResponseDto;
import com.todo.api.security.TokenService;
import com.todo.api.services.TaskService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> findAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request){
        Long userId = tokenService.getLoggedUserId(request);
        
        Page<TaskResponseDto> tasks = taskService.findAll(pageable, userId);
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findById(@PathVariable Long id, HttpServletRequest request){
        Long userId = tokenService.getLoggedUserId(request);

        TaskResponseDto task = taskService.findById(id, userId);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskCreateDto obj, HttpServletRequest request){
        Long userId = tokenService.getLoggedUserId(request);
        
        TaskResponseDto task = taskService.create(obj, userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id, @RequestBody TaskCreateDto obj){
        TaskResponseDto task = taskService.update(id, obj);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<TaskResponseDto> finishedTask(@PathVariable Long id){
        TaskResponseDto task = taskService.finishedTask(id);
        return ResponseEntity.ok().body(task);
    }    
}
