package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.dto.DocumentoUsuarioDTO;
import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.repository.DocumentoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocumentoService {
	private GenericModelMapper modelMapper;
	private DocumentoRepository documentoRepository;
	
	@Transactional(readOnly = true)
	public List<DocumentoUsuarioDTO> listarDocumentosAguardandoAprovacao() {
		return documentoRepository.findDocumentosAguardandoAprovacao().stream().map(documento -> {
			var dto = modelMapper.convertEntity(documento, DocumentoUsuarioDTO.class);
			dto.setNomeUsuario(documento.getUsuario().getNome());
			return dto;
		}).collect(Collectors.toList());
	}
	
}
