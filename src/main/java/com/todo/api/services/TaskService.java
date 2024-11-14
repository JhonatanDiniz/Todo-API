package com.todo.api.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.todo.api.entities.Task;
import com.todo.api.entities.DTOS.TaskCreateDto;
import com.todo.api.entities.DTOS.TaskResponseDto;
import com.todo.api.entities.ENUMS.StatusTask;
import com.todo.api.exceptions.ObjectNotFoundException;
import com.todo.api.repositories.TaskRepository;
import com.todo.api.repositories.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<TaskResponseDto> findAll(Pageable pageable, Long userId){
        return taskRepository.findByUserId(userId, pageable)
            .map(task ->{
                atualizarStatusTask(task);
                return new TaskResponseDto(task);
            });
    }

    public TaskResponseDto findById(Long id, Long userId){
        Task task = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Task não encontrada"));
        
        if(!task.getUser().getId().equals(userId)){
            throw new AccessDeniedException("Não encontrado tarefa!");
        }
        
        atualizarStatusTask(task);
        return new TaskResponseDto(task);
    }

    public TaskResponseDto create(TaskCreateDto obj, Long userId){
        Task task = new Task(obj);

        var user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        task.setUser(user);

        atualizarStatusTask(task);
        taskRepository.save(task);
        return new TaskResponseDto(task);        
    }

    public TaskResponseDto update(Long id, TaskCreateDto obj){
        Task newTask = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Task não encontrada"));
        newTask.atualizaDados(obj);
        atualizarStatusTask(newTask);
        taskRepository.save(newTask);
        return new TaskResponseDto(newTask);
    }

    public void delete(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Task não encontrada"));
        taskRepository.deleteById(task.getId());
    }
    
    public TaskResponseDto finishedTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Task não encontrada"));
        if(task.getStatus() != StatusTask.CONCLUIDO){
            task.setFinishedAt(LocalDate.now());
            task.setStatus(StatusTask.CONCLUIDO);
            taskRepository.save(task);
            return new TaskResponseDto(task);
        }else{
            throw new ObjectNotFoundException("Task já finalizada");
        }
    }

    public Task atualizarStatusTask(Task task){
        if(task.getStatus().equals(StatusTask.EM_ANDAMENTO) && LocalDate.now().isAfter(task.getDueDate())){
            task.setStatus(StatusTask.ATRASADO);
            taskRepository.save(task);
        }
        return task;
    }
}
