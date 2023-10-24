package com.tcc.moradiaestudantil.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MoradiaDTO {
	private Integer id;
	private String endereco;
	private String bairro;
	private String cep;
	private String descricao;
	private Double preco;
	private String tipoMoradia;
}
