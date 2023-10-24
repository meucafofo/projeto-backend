package com.tcc.moradiaestudantil.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PontoEstrategico {
	FACULDADES(0, "university");
	
	private Integer codigo;
	private String keyword;
}
