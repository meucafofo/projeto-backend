package com.tcc.moradiaestudantil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Value("${spring.mail.host}")
	private String stmpHost;
	
	@Value("${spring.mail.port}")
	private Integer stmpPort;
	
	@Value("${spring.mail.username}")
	private String stmpUser;
	
	@Value("${spring.mail.password}")
	private String stmpPass;
	
	
	@Bean
	JavaMailSender javaMailSender() {
		var mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(stmpHost);
	    mailSender.setPort(stmpPort);
	    
	    mailSender.setUsername(stmpUser);
	    mailSender.setPassword(stmpPass);
	    
	   	var props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
}
