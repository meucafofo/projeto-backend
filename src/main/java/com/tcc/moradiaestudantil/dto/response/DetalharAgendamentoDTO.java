package com.tcc.moradiaestudantil.dto.response;

import java.time.LocalDateTime;

import com.tcc.moradiaestudantil.enums.StatusAgendamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalharAgendamentoDTO {
	private Long id;
	private LocalDateTime dataVisita;
    private StatusAgendamento statusAgendamento;
    private String email;
}
