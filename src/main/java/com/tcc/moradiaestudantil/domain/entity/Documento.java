package com.tcc.moradiaestudantil.domain.entity;

import java.time.LocalDateTime;

import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "documentos")
@Getter
@Setter
public class Documento {
	@Id
	@Column(name = "id_documento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_arquivo")
	private String nome;
	
	@Column(name = "data_sit")
	private LocalDateTime dataSituacao;
	
	@Lob
	@Column(name = "conteudo")
	private byte[] arquivo;
	
	@Column(name = "tipo_conteudo")
	private String contentType;
	
	@Column(name = "status_documento")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "tipo_documento")
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
}
