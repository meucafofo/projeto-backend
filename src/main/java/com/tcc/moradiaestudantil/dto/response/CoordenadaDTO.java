package com.tcc.moradiaestudantil.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CoordenadaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -701807496061760073L;
	private Long id;
	private Double latitude;
	private Double longitude;
}
