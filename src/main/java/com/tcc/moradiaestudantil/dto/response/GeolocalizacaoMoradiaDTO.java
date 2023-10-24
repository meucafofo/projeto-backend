package com.tcc.moradiaestudantil.dto.response;

import com.tcc.moradiaestudantil.domain.entity.Coordenada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeolocalizacaoMoradiaDTO {
	private Long id;
	private Coordenada coordenada; 
}
