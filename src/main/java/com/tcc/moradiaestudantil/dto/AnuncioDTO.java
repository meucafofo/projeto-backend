package com.tcc.moradiaestudantil.dto;

import com.tcc.moradiaestudantil.domain.entity.Moradia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AnuncioDTO {
	
	private Integer id;
	private String endereco;
	private String bairro;
	private String cep;
	private String descricao;
	private Double preco;
	private String tipoMoradia;
	private String telefoneLocador;
	
	public AnuncioDTO(Moradia moradia) {
		super();
		this.id = moradia.getId();
		this.endereco = moradia.getEndereco();
		this.bairro = moradia.getBairro();
		this.cep = moradia.getCep();
		this.descricao = moradia.getDescricao();
		this.preco = moradia.getPreco();
		this.tipoMoradia = moradia.getTipoMoradia();
		this.telefoneLocador = moradia.getLocador().getTelefone();
	}
}
