package com.example.springboot.email;

public interface MailService {
	
	public void sendSimpleMessage(
		      String to, String subject, String text) ;

}
