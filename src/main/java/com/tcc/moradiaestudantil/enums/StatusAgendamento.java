package com.tcc.moradiaestudantil.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StatusAgendamento {
	PENDENTE("Pendente"), CONFIRMADO("Confirmado"), CANCELADO("Cancelado");
	
	private String statusName;
}
