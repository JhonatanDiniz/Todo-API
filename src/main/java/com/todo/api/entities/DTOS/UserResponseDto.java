package com.todo.api.entities.DTOS;

import com.todo.api.entities.User;

public record UserResponseDto(Long id, String email, String name) {
  
  public UserResponseDto(User user){
    this(user.getId(), user.getEmail(), user.getName());
  }
}
