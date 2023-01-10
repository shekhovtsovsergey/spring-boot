package com.example.springboot.dao;


import com.example.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String name);

    //void save(User user);

    User findByEmail(String email);

    List<User> findAll();

}

