package com.tcc.moradiaestudantil.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AgendamentoRepositoryTest {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Test
	void deveListarAgendamentos() {
		// setup
		var idLocador = 2L;
		
		// act
		var response = agendamentoRepository.findByUsuarioLocadorId(idLocador);
		
		// assert
		assertFalse(response.isEmpty());
	}
}
