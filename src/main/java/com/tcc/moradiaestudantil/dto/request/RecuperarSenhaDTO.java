package com.tcc.moradiaestudantil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecuperarSenhaDTO {
	private String token;
	private String senha;
}
