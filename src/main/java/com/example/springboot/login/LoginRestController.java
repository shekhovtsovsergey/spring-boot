package com.example.springboot.login;


import com.example.springboot.dao.ProductDao;
import com.example.springboot.dao.RoleDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.email.MailService;
import com.example.springboot.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginRestController {

    private final UserLoginService1 userLoginService;
    private final UserDao userDao;
    private final ProductDao productDao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleDao roleDao;
    private final MailService mailService;


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

        String x = newPass();
//        String tmp = passwordEncoder.encode(user.getPassword());
        String tmp = passwordEncoder.encode(x);

//        List <Role> dfr = new ArrayList<>();
//        dfr = userDao.findByUsername("u").getRoles();
//        user.setRoles(dfr);

        user.setPassword(tmp);
        userLoginService.save(user);
        mailService.sendSimpleMessage(user.getEmail(),"Registration", "Hello " + user.getUsername() + ", your password is "+ x);
        return "redirect:/login/register?success";
    }



    public String newPass() {
        int len = 10;
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }



}
