package com.example.springboot.login;

import com.example.springboot.dao.RoleDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.Status;
import com.example.springboot.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserLoginService1 {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public UserLoginService1(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    public User save(User user) {
        user.setStatus(Status.valueOf("ACTIVE"));
        return userDao.save(user);
    }


    public List<User> findAll() {
        return userDao.findAll();
    }
}
