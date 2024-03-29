package com.tcc.moradiaestudantil.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeolocalizacaoMoradiaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7475961515199514593L;
	
	private Long id;
	private CoordenadaDTO coordenada; 
}
