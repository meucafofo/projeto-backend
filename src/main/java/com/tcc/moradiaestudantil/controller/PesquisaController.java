package com.tcc.moradiaestudantil.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tcc.moradiaestudantil.dto.response.DetalharMoradiaDTO;
import com.tcc.moradiaestudantil.dto.response.FaculdadeAndMoradiasDTO;
import com.tcc.moradiaestudantil.dto.response.FaculdadeDTO;
import com.tcc.moradiaestudantil.service.MoradiaService;
import com.tcc.moradiaestudantil.service.PesquisaService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/seach")
@AllArgsConstructor
public class PesquisaController {
	private PesquisaService pesquisaService;
	private MoradiaService moradiaService;
	
	@GetMapping
	public ResponseEntity<List<FaculdadeDTO>> listarFaculdades() {
		return ResponseEntity.ok(pesquisaService.listarFaculdades());
	}

	@GetMapping("/moradias")
	public ResponseEntity<FaculdadeAndMoradiasDTO> procurarMoradias(@RequestParam(defaultValue = "0") final Long faculdade,
			@RequestParam(defaultValue = "todos") final String tipoMoradia,
			@RequestParam(defaultValue = "0", name = "preco_min") final BigDecimal precoMinimo,
			@RequestParam(defaultValue = "10000", name = "preco_max") final BigDecimal precoMaximo) {
		return ResponseEntity.ok(pesquisaService.pesquisarMoradias(faculdade, tipoMoradia, precoMinimo, precoMaximo));
	}
	
	@GetMapping("/moradias/{id}")
	public ResponseEntity<DetalharMoradiaDTO> detalharMoradia(@PathVariable final Long id){
		return ResponseEntity.ok(moradiaService.recuperarMoradiaAProvada(id));
	}
}
