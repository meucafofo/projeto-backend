package com.tcc.moradiaestudantil.dto.response;

import java.io.Serializable;

import com.tcc.moradiaestudantil.domain.entity.Coordenada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaculdadeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1706563221808661436L;
	
	private Long id;
	private String nome;
	private Coordenada coordenada;
}
