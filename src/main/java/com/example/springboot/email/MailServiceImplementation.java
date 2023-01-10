package com.example.springboot.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImplementation implements MailService {
@Autowired
private JavaMailSenderImpl mailSender;
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		// TODO Auto-generated method stub
		SimpleMailMessage email = new  SimpleMailMessage();
		email.setFrom("medappsuppor@rambler.ru");
		email.setTo(to);
		email.setSubject(subject);
		email.setText(text);
		
		mailSender.send(email);
	}

}
