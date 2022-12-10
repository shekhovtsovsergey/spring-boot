package com.example.springboot.dao;


import com.example.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String name);
}

