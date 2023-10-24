package com.tcc.moradiaestudantil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MoradiaEstudantilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoradiaEstudantilApplication.class, args);
	}

}
