package com.todo.api.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.entities.DTOS.UserRequestDto;
import com.todo.api.entities.DTOS.UserResponseDto;
import com.todo.api.entities.DTOS.UserSigninRequestDto;
import com.todo.api.entities.DTOS.UserSigninResponseDto;
import com.todo.api.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/user")
  public ResponseEntity<UserSigninResponseDto> signin(@RequestBody UserSigninRequestDto obj) throws AuthenticationException{
    var user = authService.signin(obj);
    return ResponseEntity.ok().body(user);
  }
  
}
