package com.tcc.moradiaestudantil.enums;

public enum TipoDenuncia {

	FRAUDE(1, "Fraude"),
	ASSEDIO(2, "Comportamento Desrespeitoso"),
	SPAM(3, "Spam");
	
	private int codigo;
	private String descricao;
	
	private TipoDenuncia(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDenuncia toEnum (Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (TipoDenuncia x : TipoDenuncia.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
	
}
