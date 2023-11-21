package com.tcc.moradiaestudantil.enums;

public enum Status {

	PENDENTE(0, "PENDENTE"), APROVADO(1, "APROVADO"), REPROVADO(2, "REPROVADO");

	private Integer codigo;
	private String descricao;

	private Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(int cod) {
		for (Status x : Status.values()) {
			if (cod == x.getCodigo()) {
				return x;
			}
		}
		throw new IllegalArgumentException("Codigo invalido inválido: " + cod);
	}
}
