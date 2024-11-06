package com.todo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}