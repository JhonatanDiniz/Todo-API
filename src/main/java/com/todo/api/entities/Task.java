package com.todo.api.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.api.entities.DTOS.TaskCreateDto;
import com.todo.api.entities.DTOS.TaskResponseDto;
import com.todo.api.entities.ENUMS.StatusTask;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate finishedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Task(TaskResponseDto obj){
        this.id = obj.id();
        this.title = obj.title();
        this.description = obj.description();
        this.createdAt = obj.createdAt();
        this.dueDate = obj.dueDate();
        this.finishedAt = obj.finishedAt();
        if(obj.user() != null){
            this.user = new User(obj.user().id(), obj.user().name(), obj.user().email(), null);
        }
    }

    public Task(TaskCreateDto obj){
        this.title = obj.title();
        this.description = obj.description();
        this.status = StatusTask.EM_ANDAMENTO;
        this.dueDate = obj.dueDate();
    }

    public void atualizaDados(TaskCreateDto obj){
        if(obj.title() != null){
            this.title = obj.title();
        }
        if(obj.description() != null){
            this.description = obj.description();
        }
    }
    
}
