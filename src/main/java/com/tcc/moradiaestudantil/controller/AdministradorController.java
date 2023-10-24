package com.tcc.moradiaestudantil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcc.moradiaestudantil.dto.MoradiaDTO;
import com.tcc.moradiaestudantil.dto.ServiceResponseDTO;
import com.tcc.moradiaestudantil.dto.response.DetalharUsuarioDTO;
import com.tcc.moradiaestudantil.dto.response.UsuarioAguardandoAprocacaoDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.exception.AuthorizationException;
import com.tcc.moradiaestudantil.service.AdministradorService;
import com.tcc.moradiaestudantil.service.MoradiaService;
import com.tcc.moradiaestudantil.service.UsuarioLogadoService;
import com.tcc.moradiaestudantil.service.UsuarioService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/administrador")
@AllArgsConstructor
public class AdministradorController {
	private UsuarioService usuarioService;
	private MoradiaService moradiaService;
	private AdministradorService administradorService;

	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioAguardandoAprocacaoDTO>> recuperarUsuariosAguardandoAprovacao() {
		verificarAdministrador();
		return ResponseEntity.ok(usuarioService.recuperarUsuariosAguardandoAprovacao());
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<DetalharUsuarioDTO> recuperarUsuarioAguardandoAprovacao(@PathVariable final Long id) {
		verificarAdministrador();
		return ResponseEntity.ok(usuarioService.findById(id));
	}

	@GetMapping("/moradias")
	public ResponseEntity<List<MoradiaDTO>> recuperarMoradiasAguardandoAprovacao() {
		verificarAdministrador();
		return ResponseEntity.ok(moradiaService.recuperarMoradiasAguardandoAprovacao());
	}

	@PutMapping("/aprovar/moradias/{id}/{aprovacao}")
	public ResponseEntity<ServiceResponseDTO> aprovarMoradia(final @PathVariable Long id, final @PathVariable("aprovacao") Status status) {
		verificarAdministrador();
		administradorService.aprovarMoradia(id, status);
		var response = new ServiceResponseDTO("Moradia Aprovada", true);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/aprovar/documentos/{id}/{aprovacao}")
	public ResponseEntity<ServiceResponseDTO> aprovarDocumento(final @PathVariable Long id, final @PathVariable("aprovacao") Status status) {
		verificarAdministrador();
		administradorService.aprovarDocumento(id, status);
		var response = new ServiceResponseDTO("Usuario Aprovado", true);
		return ResponseEntity.ok(response);
	}
	private void verificarAdministrador() {
		if (!(TipoUsuario.ADMINISTRADOR.equals(UsuarioLogadoService.autheticated().getTipoUsuario()))) {
			throw new AuthorizationException("Usuario não possui permição para acessar esse recurso!");
		}
	}
}
