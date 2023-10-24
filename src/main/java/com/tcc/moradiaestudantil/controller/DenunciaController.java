package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.domain.entity.Denuncia;
import com.tcc.moradiaestudantil.service.DenunciaService;

@RestController
@RequestMapping("/api/denuncia")
public class DenunciaController {

	@Autowired
	private DenunciaService denunciaService;

	@PostMapping
	public ResponseEntity<Denuncia> salvar(@RequestBody Denuncia denuncia) {

		return ResponseEntity.status(HttpStatus.CREATED).body(denunciaService.inserirDenuncia(denuncia));
	}

	@GetMapping
	public ResponseEntity<List<Denuncia>> listarTodos() {
		return ResponseEntity.ok().body(denunciaService.listarDenuncia());
	}

	@PostMapping("/denunciar")
	public ResponseEntity<Denuncia> inserirDenuncia(@RequestBody Denuncia denuncia) {
		Denuncia entity = denunciaService.inserirDenuncia(denuncia);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
}
