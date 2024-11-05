package com.todo.api.entities.DTOS;

import java.time.LocalDate;

public record TaskCreateDto(String title, String description, LocalDate dueDate) {
    
}
