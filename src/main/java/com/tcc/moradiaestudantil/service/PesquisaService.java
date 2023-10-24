package com.tcc.moradiaestudantil.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.dto.response.FaculdadeAndMoradiasDTO;
import com.tcc.moradiaestudantil.dto.response.FaculdadeDTO;
import com.tcc.moradiaestudantil.dto.response.GeolocalizacaoMoradiaDTO;
import com.tcc.moradiaestudantil.repository.FaculdadeRepository;
import com.tcc.moradiaestudantil.repository.MoradiaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PesquisaService {

	private FaculdadeRepository faculdadeRepository;
	private MoradiaRepository moradiaRepository;
	private GenericModelMapper modelMapper;

	@Transactional(readOnly = true)
	public List<FaculdadeDTO> listarFaculdades() {
		return faculdadeRepository.findAll().stream()
				.map(faculdade -> modelMapper.convertEntity(faculdade, FaculdadeDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)	
	public FaculdadeAndMoradiasDTO pesquisarMoradias(Long id, String tipoMoradia, BigDecimal precoMinimo,
			BigDecimal precoMaximo) {
		var faculdade = faculdadeRepository.findById(id).orElse(null);
		var moradias = moradiaRepository.pesquisarMoradiaPelosParametros(tipoMoradia, precoMinimo, precoMaximo);

		var res = new FaculdadeAndMoradiasDTO();
		res.setFaculdade(Objects.nonNull(faculdade) ? modelMapper.convertEntity(faculdade, FaculdadeDTO.class) : null);
		res.setMoradias(
				moradias.stream().map(moradia -> modelMapper.convertEntity(moradia, GeolocalizacaoMoradiaDTO.class))
						.collect(Collectors.toList()));

		return res;
	}
}
