package com.tcc.moradiaestudantil.service;

import org.springframework.stereotype.Service;

import com.tcc.moradiaestudantil.domain.entity.Coordenada;
import com.tcc.moradiaestudantil.domain.entity.Faculdade;
import com.tcc.moradiaestudantil.repository.FaculdadeRepository;
import com.tcc.moradiaestudantil.wsclient.dto.LocalizacaoDTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FaculdadeService {
	private FaculdadeRepository faculdadeRepository;
	
	@Transactional(TxType.REQUIRES_NEW)
	public void cadastrarFaculdade(LocalizacaoDTO dto) {
		var faculdade = new Faculdade();
		var coordenada = new Coordenada();
		
		faculdade.setNome(dto.getName());
		faculdade.setLocalidade(dto.getVicinity());
		
		coordenada.setLatitude(dto.getGeometry().getLocation().getLat());
		coordenada.setLongitude(dto.getGeometry().getLocation().getLng());
		faculdade.setCoordenada(coordenada);
		
		faculdadeRepository.save(faculdade);
	}
}
