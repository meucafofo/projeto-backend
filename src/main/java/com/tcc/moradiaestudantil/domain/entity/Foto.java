package com.tcc.moradiaestudantil.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fotos")
@Getter
@Setter
public class Foto {
	@Id
	@Column(name = "id_foto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_arquivo")
	private String nome;
	
	@Lob
	@Column(name = "conteudo")
	private byte[] arquivo;
	
	@Column(name = "tipo_conteudo")
	private String contentType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_moradia", referencedColumnName = "id_moradia", unique = false)
	private Moradia moradia;
}
