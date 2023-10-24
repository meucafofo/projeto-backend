package com.tcc.moradiaestudantil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSenhaDTO {
	private String senhaAtual;
	private String senhaNova;
}
