package com.todo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.api.entities.User;
import com.todo.api.entities.DTOS.UserRequestDto;
import com.todo.api.entities.DTOS.UserResponseDto;
import com.todo.api.exceptions.ObjectFoundException;
import com.todo.api.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserResponseDto create(UserRequestDto obj){
    userRepository.findByEmail(obj.email()).ifPresent((userFound) -> {
      throw new ObjectFoundException("Usuário já está cadastrado");
    });
    User user = new User(obj);
    var password = passwordEncoder.encode(obj.password());
    user.setPassword(password);
    userRepository.save(user);
    return new UserResponseDto(user);
  }
  
}
