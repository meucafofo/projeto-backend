package com.tcc.moradiaestudantil.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.tcc.moradiaestudantil.domain.entity.Agendamento;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgendamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime horario;
	private String status;
	
	public AgendamentoDTO() {
	}
	
	public AgendamentoDTO(Long id, LocalDateTime horario, String status) {
		this.id = id;
		this.horario = horario;
		this.status = status;
	}
	
	public AgendamentoDTO(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.horario = agendamento.getDataVisita();
		this.status = agendamento.getStatus();
	}
}
