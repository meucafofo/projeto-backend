package com.tcc.moradiaestudantil.enums;

public enum Status {

	PENDENTE(0, "pendente"), APROVADO(1, "aprovado"), REPROVADO(2, "reprovado");

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
