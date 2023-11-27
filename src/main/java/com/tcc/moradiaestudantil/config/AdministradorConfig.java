package com.tcc.moradiaestudantil.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AdministradorConfig {
	
	@Value("${meucafofo.default.admin.pass}")
	private String senha;
	
	@NonNull
	private UsuarioRepository usuarioRepository;

	@NonNull
	private PasswordEncoder encoder;
	
	@Bean
	Usuario usuarioSystema() {
		var usuario = usuarioRepository.recuperarUsuarioSystema();
		if (usuario.isEmpty()) {
			var usuarioPadrao = new Usuario();
			usuarioPadrao.setNome("Systema");
			usuarioPadrao.setDataNasc(LocalDate.of(1900, 1, 1));
			usuarioPadrao.setEmail("system@meucafofo.github.io");
			usuarioPadrao.setSenha(encoder.encode(this.senha));
			usuarioPadrao.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
			usuarioPadrao.setSexo("Outro");
			log.info("Senha do padrão: " + senha);
			return usuarioRepository.save(usuarioPadrao);
		}
		
		return usuario.get();
	}
}
