package com.todo.api.entities.DTOS;

import com.todo.api.entities.User;

public record UserSigninResponseDto(Long id, String name, String email, String token) {
  public UserSigninResponseDto(User user, String token){
    this(user.getId(), user.getName(), user.getEmail(), token);
  }
  
}
