package com.todo.api.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.api.entities.Task;
import com.todo.api.entities.User;
import com.todo.api.entities.ENUMS.StatusTask;
import com.todo.api.repositories.TaskRepository;
import com.todo.api.repositories.UserRepository;

@Service
public class DBService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void instaciaDB(){

    var senha = passwordEncoder.encode("Tere2664");

    User user = new User(null, "Jhonatan Gomes Diniz", "jhonatangomesdiniz@gmail.com", senha);
    userRepository.saveAll(Arrays.asList(user));

    Task task1 = new Task(null, "Estudar Java", "Aprofundar nos assuntos Java", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-03"), null, user);
    Task task2 = new Task(null, "Revisar Spring Boot", "Revisar conceitos e práticas de Spring Boot", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-10"), null, user);
    Task task3 = new Task(null, "Aprender React", "Aprender fundamentos do React.js", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-01"), null, user);
    Task task4 = new Task(null, "Criar API REST", "Desenvolver uma API REST com Node.js", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-25"), null, user);
    Task task5 = new Task(null, "Desenhar Banco de Dados", "Criar diagramas de banco de dados para novo projeto", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-06"), null, user);
    Task task6 = new Task(null, "Estudar Microservices", "Entender arquitetura de microservices", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-02"), null, user);
    Task task7 = new Task(null, "Configurar CI/CD", "Configurar pipeline de CI/CD usando Jenkins", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-30"), null, user);
    Task task8 = new Task(null, "Aprender Docker", "Aprender a containerizar aplicações com Docker", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-01"), null, user);
    Task task9 = new Task(null, "Melhorar UX", "Implementar melhorias de UX no aplicativo", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-27"), null, user);
    Task task10 = new Task(null, "Escrever Testes Unitários", "Escrever testes unitários para cobertura de código", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-09"), null, user);
    Task task11 = new Task(null, "Realizar Code Review", "Revisar código dos colegas de equipe", StatusTask.EM_ANDAMENTO, null, LocalDate.parse("2024-11-12"), null, user);

    taskRepository.saveAll(Arrays.asList(task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, task11));
  }
  
}
