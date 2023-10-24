package com.tcc.moradiaestudantil.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaculdadeAndMoradiasDTO {
	private FaculdadeDTO faculdade;
	private List<GeolocalizacaoMoradiaDTO> moradias;
}
