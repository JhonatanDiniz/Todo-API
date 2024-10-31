package com.todo.api.entities.DTOS;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.todo.api.entities.Task;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskResponseDto(Long id, String title, String description, String status, LocalDate createdAt, LocalDate duDate, LocalDate finishedAt) {

    public TaskResponseDto(Task obj){
        this(
            obj.getId(), 
            obj.getTitle(), 
            obj.getDescription(), 
            obj.getStatus().getDescription(),
            obj.getCreatedAt(),
            obj.getDueDate(),
            obj.getFinishedAt()
        );
    }
    
}
