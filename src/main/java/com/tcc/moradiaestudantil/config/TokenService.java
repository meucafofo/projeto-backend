package com.tcc.moradiaestudantil.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.dto.CredentialDTO;
import com.tcc.moradiaestudantil.dto.UsuarioDTO;

@Service
public class TokenService {

	private static final ZoneOffset BRAZIL_ZONE_OFFSET = ZoneOffset.of("-03:00");
	private static final Integer TIMEOUT_IN_MINUTES = 15;

	
	@Value("${api.security.token.secret}")
	private String secret;
	

	public CredentialDTO generateToken(Usuario user) {
		try {
			var time = genExpirationDate();
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer("auth-api").withSubject(user.getEmail()).withExpiresAt(time)
					.sign(algorithm);
			return new CredentialDTO(token, Long.valueOf(TIMEOUT_IN_MINUTES * 60), UsuarioDTO.builder().id(user.getId()).nome(user.getNome())
					.email(user.getEmail()).tipoUsuario(user.getTipoUsuario()).build());
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro enquanto gera o token", e);
		}
	}

	public String validateToken(String token) {

		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}

	private Instant genExpirationDate() {
		return LocalDateTime.now().plusMinutes(TIMEOUT_IN_MINUTES).toInstant(BRAZIL_ZONE_OFFSET);
	}
}
