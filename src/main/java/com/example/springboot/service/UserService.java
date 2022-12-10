package com.example.springboot.service;

import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    private final UserDao userDao;


    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }







}
