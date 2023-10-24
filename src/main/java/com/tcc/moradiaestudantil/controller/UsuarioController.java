package com.tcc.moradiaestudantil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.moradiaestudantil.dto.AtualizarUsuarioDTO;
import com.tcc.moradiaestudantil.dto.ServiceResponseDTO;
import com.tcc.moradiaestudantil.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.dto.request.UpdateSenhaDTO;
import com.tcc.moradiaestudantil.dto.response.DetalharUsuarioDTO;
import com.tcc.moradiaestudantil.enums.TipoDocumento;
import com.tcc.moradiaestudantil.service.UsuarioLogadoService;
import com.tcc.moradiaestudantil.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {

	private UsuarioService usuarioService;

	private GenericModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<ServiceResponseDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastarUsuario(usuario));
	}

	@GetMapping
	public ResponseEntity<DetalharUsuarioDTO> recupearUsuario() {
		var dto = modelMapper.convertEntity(usuarioService.findById(UsuarioLogadoService.autheticated().getId()),
				DetalharUsuarioDTO.class);
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping
	public ResponseEntity<ServiceResponseDTO> updateUsuario(@RequestBody AtualizarUsuarioDTO usuario) {
		usuarioService.updateUsuario(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResponseDTO("Cadastro Atualizado!", true));
	}

	@PutMapping("/senha")
	public ResponseEntity<ServiceResponseDTO> updateSenha(@RequestBody UpdateSenhaDTO senhas) {
		var serviceResponse = usuarioService.updateSenha(senhas.getSenhaAtual(), senhas.getSenhaNova());
		return ResponseEntity.ok(serviceResponse);
	}

	@PostMapping(path = "/documentos/{tipoDocumento}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ServiceResponseDTO> cadastrarDocumento(@PathVariable final String tipoDocumento,
			@RequestParam("file") final MultipartFile file) {
		var usuario = UsuarioLogadoService.autheticated();

		usuarioService.cadastrarDocumento(usuario.getId(), TipoDocumento.valueOf(tipoDocumento), file);

		var responseCreated = new ServiceResponseDTO();
		responseCreated.setMessage("Comprovante Cadastrado!");
		responseCreated.setStatus(true);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseCreated);
	}

	@PostMapping("/recuperar-senha")
	public ResponseEntity<Void> solicitarRecuperacaoSenha(@RequestParam("email") final String email) {
		usuarioService.solicitarRecuperacaoSenha(email);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/recuperar-senha/{token}")
	public ResponseEntity<Void> recuperarSenha(@PathVariable final String token, @RequestParam("senha") final String senha) {
		usuarioService.recuperarSenha(token, senha);
		return ResponseEntity.ok().build();
	}
}
