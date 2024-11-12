package com.todo.api.security;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.todo.api.entities.User;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;
  
  public String gerarToken(User user){
    try {
      var algoritimo = Algorithm.HMAC256(secret);
      return JWT.create()
                .withIssuer("Efficiently")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .sign(algoritimo);

    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro ao gerar token", e);
    }
  }

  public String validarToken(String tokenJWT){
    try {
      var algoritimo = Algorithm.HMAC256(secret);
      return JWT.require(algoritimo)
                .withIssuer("Efficiently")
                .build()
                .verify(tokenJWT)
                .getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Token JWT inválido ou expirado");
    }
  }
}
