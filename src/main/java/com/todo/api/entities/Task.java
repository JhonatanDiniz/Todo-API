package com.todo.api.entities;

import com.todo.api.entities.DTOS.TaskResponseDto;
import com.todo.api.entities.ENUMS.StatusTask;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private StatusTask status;

    public Task(TaskResponseDto obj){
        this.id = obj.id();
        this.title = obj.title();
        this.description = obj.description();
        this.status = obj.status();
    }
    
}
