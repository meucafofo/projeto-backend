package com.tcc.moradiaestudantil.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tcc.moradiaestudantil.domain.entity.Usuario;

public class UsuarioLogadoService {

	
	public static Usuario autheticated() {
		try {
			return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		} catch (Exception e) {
			return null;
		}
	}
}
