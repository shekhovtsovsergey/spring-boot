package com.example.springboot.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmailController {
    @Autowired
    private MailServiceImplementation mail;
    @GetMapping("/email")
    public String publishMessage(@RequestBody Email email) {
        mail.sendSimpleMessage(email.getEmail(), "Password", "Hi This is your password");
        return "Email sent";
    }
}
