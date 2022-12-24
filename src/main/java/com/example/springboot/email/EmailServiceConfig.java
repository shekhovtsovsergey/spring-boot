package com.example.springboot.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class EmailServiceConfig  {

	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
			
	@Bean
    public JavaMailSenderImpl mailSender() {

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    //For aws remove the  below line
	    //props.put("mail.smtp.ssl.trust", "smtp.rambler.ru");
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        //javaMailSender.setProtocol("SMTP");
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setPassword(password);
        javaMailSender.setUsername(username);
        javaMailSender.setJavaMailProperties(props);

        return javaMailSender;
    }

}
