package com.tcc.moradiaestudantil.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.tcc.moradiaestudantil.enums.StatusAgendamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="agendamentos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agendamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_agenda")
	private Long id;
	
	@Column(name="data_hora")
	private LocalDateTime dataVisita;
	
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_moradia")
    private Moradia moradia;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "status_agendamento")
    private StatusAgendamento statusAgendamento;
}
