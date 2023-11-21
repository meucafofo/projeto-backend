package com.tcc.moradiaestudantil.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class AtualizarUsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String cgc;
	private LocalDate dataNasc;
	private String sexo;
	private String email;
	private String telefone;
}
