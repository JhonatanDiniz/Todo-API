package com.todo.api.entities.DTOS;

import com.todo.api.entities.Task;
import com.todo.api.entities.ENUMS.StatusTask;

public record TaskResponseDto(Long id, String title, String description, StatusTask status) {

    public TaskResponseDto(Task obj){
        this(obj.getId(), obj.getTitle(), obj.getDescription(), obj.getStatus());
    }
    
}
