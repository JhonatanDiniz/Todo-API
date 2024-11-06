package com.todo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.api.entities.User;
import com.todo.api.entities.DTOS.UserRequestDto;
import com.todo.api.entities.DTOS.UserResponseDto;
import com.todo.api.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserResponseDto create(UserRequestDto obj){
    User user = new User(obj);
    userRepository.save(user);
    return new UserResponseDto(user);
  }
  
}
