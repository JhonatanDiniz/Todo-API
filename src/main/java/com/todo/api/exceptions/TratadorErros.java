package com.todo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TratadorErros {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<String> erro404(ObjectNotFoundException ex){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(ObjectFoundException.class)
  public ResponseEntity<String> erro404(ObjectFoundException ex){
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }
  
  
}
