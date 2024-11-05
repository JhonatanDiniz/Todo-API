package com.todo.api.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.api.entities.Task;
import com.todo.api.entities.ENUMS.StatusTask;
import com.todo.api.repositories.TaskRepository;

@Service
public class DBService {

  @Autowired
  private TaskRepository taskRepository;

  public void instaciaDB(){

    Task task1 = new Task(null, "Estudar Java", "Aprofundar nos assuntos Java", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-03"), null);
    Task task2 = new Task(null, "Revisar Spring Boot", "Revisar conceitos e práticas de Spring Boot", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-10"), null);
    Task task3 = new Task(null, "Aprender React", "Aprender fundamentos do React.js", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-01"), null);
    Task task4 = new Task(null, "Criar API REST", "Desenvolver uma API REST com Node.js", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-25"), null);
    Task task5 = new Task(null, "Desenhar Banco de Dados", "Criar diagramas de banco de dados para novo projeto", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-06"), null);
    Task task6 = new Task(null, "Estudar Microservices", "Entender arquitetura de microservices", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-02"), null);
    Task task7 = new Task(null, "Configurar CI/CD", "Configurar pipeline de CI/CD usando Jenkins", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-30"), null);
    Task task8 = new Task(null, "Aprender Docker", "Aprender a containerizar aplicações com Docker", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-01"), null);
    Task task9 = new Task(null, "Melhorar UX", "Implementar melhorias de UX no aplicativo", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-27"), null);
    Task task10 = new Task(null, "Escrever Testes Unitários", "Escrever testes unitários para cobertura de código", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-09"), null);
    Task task11 = new Task(null, "Realizar Code Review", "Revisar código dos colegas de equipe", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-12"), null);

    taskRepository.saveAll(Arrays.asList(task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, task11));
  }
  
}
