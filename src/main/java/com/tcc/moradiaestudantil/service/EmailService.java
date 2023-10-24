package com.tcc.moradiaestudantil.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

	private JavaMailSender emailSender;
	
	public void sendMessage(
		      String to, String subject, String text) {
		        var message = new SimpleMailMessage(); 
		        message.setFrom("moises.caldas@hotmail.com");
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText(text);
		        emailSender.send(message);
	}
}
