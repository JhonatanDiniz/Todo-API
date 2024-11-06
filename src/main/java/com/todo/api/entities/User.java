package com.todo.api.entities;

import org.hibernate.validator.constraints.Length;

import com.todo.api.entities.DTOS.UserRequestDto;
import com.todo.api.entities.DTOS.UserResponseDto;
import com.todo.api.entities.DTOS.UserSigninDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Email
  @Column(unique = true)
  private String email;

  @Length(min = 8, max = 16)
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
