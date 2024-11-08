package com.todo.api.services;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.api.entities.DTOS.UserSigninRequestDto;
import com.todo.api.entities.DTOS.UserSigninResponseDto;
import com.todo.api.exceptions.ObjectNotFoundException;
import com.todo.api.repositories.UserRepository;
import com.todo.api.security.TokenService;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private TokenService tokenService;

  public UserSigninResponseDto signin(UserSigninRequestDto obj) throws AuthenticationException{
    //Verificar se existe usuário com e-mail cadastrado
    var user = userRepository.findByEmail(obj.email()).orElseThrow(
      () -> {
        throw new ObjectNotFoundException("Usuário não encontrado!");
      });

    //Verifica se a senha informada é igual a salva no banco
    var passwordMatcher = passwordEncoder.matches(obj.password(), user.getPassword());

    //Se senha diferente lança erro
    if(!passwordMatcher){
      throw new AuthenticationException();    }

    //Se não for diferente gera Token
    String token = tokenService.gerarToken(user);
    return new UserSigninResponseDto(user, token);
  }
  
}
