package com.tcc.moradiaestudantil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.entity.Comprovante;
import com.tcc.moradiaestudantil.repository.ComprovanteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ComprovanteService {
	private ComprovanteRepository comprovanteRepository;
	
	@Transactional
	public void salvar(Comprovante comprovante) {
		comprovanteRepository.save(comprovante);
	}
}
