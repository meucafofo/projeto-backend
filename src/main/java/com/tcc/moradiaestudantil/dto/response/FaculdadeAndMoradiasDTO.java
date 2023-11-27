package com.tcc.moradiaestudantil.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaculdadeAndMoradiasDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4357737780041463646L;
	
	private FaculdadeDTO faculdade;
	private List<GeolocalizacaoMoradiaDTO> moradias;
}
