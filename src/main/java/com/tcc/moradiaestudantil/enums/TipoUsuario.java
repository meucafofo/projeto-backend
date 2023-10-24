package com.tcc.moradiaestudantil.enums;

public enum TipoUsuario {

	ADMINISTRADOR(0, "Administrador"),
	ALUNO(1, "Aluno"),
	LOCADOR(2, "Locador");
	
	private Integer codigo;
	private String descricao;
	
	private TipoUsuario(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoUsuario toEnum (int cod) {
		for (TipoUsuario x : TipoUsuario.values()) {
			if(x.getCodigo() == cod) {
				return x;
			}
		}
		throw new IllegalArgumentException("Codigo inválido: " + cod);
	}
	
}
