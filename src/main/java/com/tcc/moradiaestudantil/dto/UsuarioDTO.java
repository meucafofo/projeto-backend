package com.tcc.moradiaestudantil.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tcc.moradiaestudantil.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@Builder
public class UsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String cgc;
	private String dataNasc;
	private String sexo;
	private String email;
	private String senha;
	private String telefone;
	private TipoUsuario tipoUsuario;
}
