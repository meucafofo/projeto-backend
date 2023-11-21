package com.tcc.moradiaestudantil.domain.entity;

import com.tcc.moradiaestudantil.enums.TipoSolicitacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "solicitacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Solicitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitacao")
	private Long id;
	
	private String token;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_solicitacao")
	private TipoSolicitacao tipoSolicitacao;
	
	private Boolean usado;

	@ManyToOne
	@JoinColumn(name = "id_usuario_solicitado", referencedColumnName = "id_usuario")
	private Usuario usuario;
}
