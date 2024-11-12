package com.todo.api.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    SecurityContextHolder.getContext().setAuthentication(null);
       
    String tokenJWT = recuperarToken(request);

    if (tokenJWT != null) {
      var subjectToken = tokenService.validarToken(tokenJWT);
      if(subjectToken.isEmpty()){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
      request.setAttribute("user_id", subjectToken);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);
  }


  
  private String recuperarToken(HttpServletRequest request){
    String authorizationToken = request.getHeader("Authorization");
  
    if(authorizationToken != null){
      return authorizationToken.replace("Bearer ", "");
    }
    return null;
  }
}

