package com.example.springboot.dao;


import com.example.springboot.entity.Cart;
import com.example.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Long> {

}
