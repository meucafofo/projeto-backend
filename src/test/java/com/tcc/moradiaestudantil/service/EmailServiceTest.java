package com.tcc.moradiaestudantil.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EmailServiceTest {
	
	@Autowired
	private EmailService emailService;
	
	@Test
	void deveEnviarEmail() {
		assertDoesNotThrow(() -> emailService.sendMessage("vitoriapami436@gmail.com", "Teste", "Isso é uma mensagem de Teste"));
	}
}
