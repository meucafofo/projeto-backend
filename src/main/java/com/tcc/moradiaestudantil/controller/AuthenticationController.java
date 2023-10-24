package com.tcc.moradiaestudantil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.config.TokenService;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.dto.AuthenticationDTO;
import com.tcc.moradiaestudantil.service.UsuarioLogadoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

	private AuthenticationManager authenticationManager;

	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse servletResponse) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((Usuario) auth.getPrincipal());
		return ResponseEntity.ok(token);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(HttpServletResponse servletResponse) {
		Usuario user = UsuarioLogadoService.autheticated();
		var token = tokenService.generateToken(user);
		return ResponseEntity.ok(token);
	}

}
