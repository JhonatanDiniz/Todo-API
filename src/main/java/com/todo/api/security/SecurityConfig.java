package com.todo.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private SecurityFilter securityFilter;

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
          .requestMatchers("/auth/user").permitAll()
          .requestMatchers("/user").permitAll()
          .requestMatchers("/h2-console/**").permitAll()
          .anyRequest().authenticated();
      })
      .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
      ;
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
}
