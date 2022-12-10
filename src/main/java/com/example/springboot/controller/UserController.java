package com.example.springboot.controller;

import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }



}
