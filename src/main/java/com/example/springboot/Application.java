package com.example.springboot;

import com.example.springboot.dao.OrderDao;
import com.example.springboot.dao.ProductDao;
import com.example.springboot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


/*
        OrderDao orderDao;
        ProductDao productDao;
        UserDao userDao;


        System.out.println(productDao.findAll());
        System.out.println(userDao.findAll());
        System.out.println(orderDao.findAll());
*/

    }

}
