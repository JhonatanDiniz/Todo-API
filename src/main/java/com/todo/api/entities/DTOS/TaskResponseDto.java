package com.todo.api.entities.DTOS;

import com.todo.api.entities.Task;

public record TaskResponseDto(Long id, String title, String description) {

    public TaskResponseDto(Task obj){
        this(obj.getId(), obj.getTitle(), obj.getDescription());
    }
    
}
