package com.tcc.moradiaestudantil.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.tcc.moradiaestudantil.domain.entity.Coordenada;
import com.tcc.moradiaestudantil.domain.entity.Faculdade;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FaculdadeRepositoryTest {
	
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	
	@Test void devePersistirFaculdadeComCoordenadas() {
		// setup
		var coordenada = new Coordenada(null, 0D, 0D);
		var faculdade = new Faculdade(null, "Universidade", "Local", coordenada);
		
		// act
		var res = faculdadeRepository.save(faculdade);
		
		// assert
		assertTrue(Objects.nonNull(res));
	}
}
