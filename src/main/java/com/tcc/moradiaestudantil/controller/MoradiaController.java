package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.dto.DocumentoDTO;
import com.tcc.moradiaestudantil.dto.MoradiaDTO;
import com.tcc.moradiaestudantil.dto.ServiceResponseDTO;
import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.dto.request.AgendamentoDTO;
import com.tcc.moradiaestudantil.dto.response.DetalharAgendamentoDTO;
import com.tcc.moradiaestudantil.dto.response.DetalharMoradiaDTO;
import com.tcc.moradiaestudantil.enums.StatusAgendamento;
import com.tcc.moradiaestudantil.service.MoradiaService;
import com.tcc.moradiaestudantil.service.UsuarioLogadoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/moradias")
@AllArgsConstructor
public class MoradiaController {

	private MoradiaService moradiaService;

	private GenericModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<MoradiaDTO> salvar(@RequestBody MoradiaDTO dto) {
		var responseCreated = moradiaService.cadastrarMoradia(modelMapper.convertEntity(dto, Moradia.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(responseCreated);
	}

	@PostMapping(path = "/{id}/comprovante", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ServiceResponseDTO> cadastrarComprovante(@PathVariable final Long id,
			@RequestParam("file") final MultipartFile file) {
		moradiaService.cadastrarComprovante(id, file);
		var responseCreated = new ServiceResponseDTO();
		responseCreated.setMessage("Comprovante Cadastrado!");
		responseCreated.setStatus(true);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseCreated);
	}

	@PostMapping(path = "/{id}/fotos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<DocumentoDTO> cadastrarFoto(@PathVariable final Long id,
			@RequestParam("file") final MultipartFile file) {
		var documento = moradiaService.cadastrarFoto(id, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(documento);
	}

	@GetMapping("/locador/anuncios")
	public ResponseEntity<List<MoradiaDTO>> listarMoradiaPorLocador() {
		var usuario = UsuarioLogadoService.autheticated();
		var listMoradiaPage = moradiaService.listarMoradiaPorLocador(usuario);
		return ResponseEntity.ok().body(listMoradiaPage);
	}

	@GetMapping
	public ResponseEntity<List<Moradia>> listarTodos() {
		List<Moradia> moradia = moradiaService.listarMoradia();
		return ResponseEntity.ok().body(moradia);
	}

	@GetMapping("/locador/anuncios/{id}")
	public ResponseEntity<DetalharMoradiaDTO> recuperarMoradia(@PathVariable("id") final Long id) {
		var moradia = moradiaService.recuperarMoradia(id);
		return ResponseEntity.ok().body(moradia);
	}

	@DeleteMapping("/locador/anuncios/{id}")
	public ResponseEntity<Void> encerrarMoradia(@PathVariable("id") final Long id) {
		var usuario = UsuarioLogadoService.autheticated();
		moradiaService.encerrarMoradia(id, usuario);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/agendamentos")
	public ResponseEntity<ServiceResponseDTO> agendarVisita(@RequestBody final AgendamentoDTO dto) {
		var response = moradiaService.agendarVisita(dto.getIdMoradia(), dto.getEmailAluno(), dto.getHorario());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/agendamentos")
	public ResponseEntity<List<DetalharAgendamentoDTO>> recuperarAgendamentos(){
		var usuario = UsuarioLogadoService.autheticated();
		var response = moradiaService.recuperarAgendamentos(usuario.getId());
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/agendamentos/{id}/{status}")
	public ResponseEntity<ServiceResponseDTO> atualizarStatusAgendamento(@PathVariable("id") final Long id, @PathVariable("status") final StatusAgendamento status){
		var response = moradiaService.atualizarStatusAgendamento(id, status);
		return ResponseEntity.ok(response);
	}
}
