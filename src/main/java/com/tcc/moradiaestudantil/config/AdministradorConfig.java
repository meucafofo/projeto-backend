package com.tcc.moradiaestudantil.config;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@AllArgsConstructor
@Slf4j
public class AdministradorConfig {
	
	private UsuarioRepository usuarioRepository;
	private PasswordEncoder encoder;
	
	@Bean
	Usuario usuarioSystema() {
		var usuario = usuarioRepository.recuperarUsuarioSystema();
		if (usuario.isEmpty()) {
			var usuarioPadrao = new Usuario();
			var senha = UUID.randomUUID().toString();
			
			usuarioPadrao.setNome("Systema");
			usuarioPadrao.setDataNasc(LocalDate.of(1900, 1, 1));
			usuarioPadrao.setEmail("system@meucafofo.github.io");
			usuarioPadrao.setSenha(encoder.encode(senha));
			usuarioPadrao.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
			usuarioPadrao.setSexo("Outro");
			log.info("Senha do padrão: " + senha);
			return usuarioRepository.save(usuarioPadrao);
		}
		
		return usuario.get();
	}
}
