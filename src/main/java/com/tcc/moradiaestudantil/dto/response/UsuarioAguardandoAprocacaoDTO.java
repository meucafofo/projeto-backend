package com.tcc.moradiaestudantil.dto.response;

import com.tcc.moradiaestudantil.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioAguardandoAprocacaoDTO {
	private Long id;
	private String nome;
	private TipoUsuario tipoUsuario;
}
