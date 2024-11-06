package com.todo.api.entities;

import com.todo.api.entities.DTOS.UserRequestDto;
import com.todo.api.entities.DTOS.UserResponseDto;
import com.todo.api.entities.DTOS.UserSigninDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_user")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Column(unique = true)
  private String email;
  private String password;

  public User(UserResponseDto obj){
    this.id = obj.id();
    this.email = obj.email();
    this.name = obj.name();
  }

  public User(UserRequestDto obj){
    this.name = obj.name();
    this.email = obj.email();
    this.password = obj.password();
  }

  public User(UserSigninDto obj){
    this.email = obj.email();
    this.password = obj.password();
  }
}
