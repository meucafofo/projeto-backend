package com.tcc.moradiaestudantil.domain.entity;

import java.sql.Date;

import com.tcc.moradiaestudantil.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comprovantes")
@Getter
@Setter
public class Comprovante {
	@Id
	@Column(name = "id_comprovante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_arquivo")
	private String nome;
	
	@Column(name = "data_sit")
	private Date dataSituacao;
	
	@Lob
	@Column(name = "conteudo")
	private byte[] arquivo;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_moradia", referencedColumnName = "id_moradia")
	private Moradia moradia;
}
