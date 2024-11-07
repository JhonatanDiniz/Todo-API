package com.todo.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http
      .csrf(csrf -> csrf.disable())
      .headers(headers -> {
        headers
          .frameOptions(frameOptions -> frameOptions.sameOrigin());
      })
      .authorizeHttpRequests(auth -> {
        auth
          .requestMatchers("/user").permitAll()
          .requestMatchers("/h2-console/**").permitAll()
          .anyRequest().authenticated();
      });
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
}
