package com.tcc.moradiaestudantil.wscliente;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.tcc.moradiaestudantil.wsclient.GoogleMapsClient;

@SpringBootTest
@ActiveProfiles("test")
class GoogleMapsClienteTest {
	
	@Autowired
	private GoogleMapsClient googleMapsCliente;
	
	@Test void deveRecuperarFaculdadesEmBrasilia() {
		// setup
		var latitude = -15.7801D;
		var longitude = -47.9292D;
		var keyword = "university";
		var radius = 30000;
		
		// act
		var res = googleMapsCliente.pesquisarLocalizacoes(latitude, longitude, keyword, radius, null);
		System.out.println(res);
		
		// assert
		assertFalse(res.getResults().isEmpty());
	}
	
	@Test void deveRecuperarEndereco() {
		// setup
		var query = "SCIA Qd. 13 Cj. 04 Lotes 01/02 – Zona Industrial - Guará, Brasília - DF, 71250-225";
		
		// act
		var rex = googleMapsCliente.pesquisarEndereco(query);
		System.out.println(rex);
		
		// assert
		assertFalse(rex.getResults().isEmpty());		
	}
}
