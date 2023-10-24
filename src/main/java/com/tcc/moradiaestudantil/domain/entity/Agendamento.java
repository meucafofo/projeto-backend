package com.tcc.moradiaestudantil.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Agendamento")
public class Agendamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_agenda")
	private Long id;
	
	@Column(name="data_hora")
	private LocalDateTime dataVisita;
	
	@Column(name="status")
	private String status;
	
    // Relacionamentos
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Usuario Usuario;

    @ManyToOne
    @JoinColumn(name = "id_moradia")
    private Moradia moradia;
	
}
