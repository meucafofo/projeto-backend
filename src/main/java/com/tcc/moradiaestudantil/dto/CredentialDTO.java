package com.tcc.moradiaestudantil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class CredentialDTO {
	private String token;
	private Long expiresAt;
	private UsuarioDTO principal;
}
