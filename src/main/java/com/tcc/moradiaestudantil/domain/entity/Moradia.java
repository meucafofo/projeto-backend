package com.tcc.moradiaestudantil.domain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="moradias")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Moradia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484372933319979044L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_moradia")
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "preco")
	private Double preco;
	
	@Column(name = "tipo_de_moradia")
	private String tipoMoradia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_locador")
	private Usuario locador;
	
    @OneToMany(mappedBy = "moradia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agendamento> agendamentos;
    
	@OneToOne(mappedBy = "moradia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Comprovante comprovante;
	
	@OneToMany(mappedBy = "moradia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Foto> fotos;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_coordenada", referencedColumnName = "id_coordenada")
	private Coordenada coordenada;
}
