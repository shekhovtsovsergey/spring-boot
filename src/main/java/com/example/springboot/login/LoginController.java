package com.example.springboot.login;


import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserLoginService userLoginService;
    private final UserDao userDao;


    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/form")
    public String loginForm() {
        return "login-form";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }



    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<User> users = userLoginService.findAll();
        model.addAttribute("users", users);
        return "users";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        User existing = userDao.findByUsername(user.getUsername());

        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userLoginService.save(user);
        return "redirect:/login/register?success";
    }


}
