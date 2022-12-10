package com.example.springboot.dao;


import com.example.springboot.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Long> {

}
