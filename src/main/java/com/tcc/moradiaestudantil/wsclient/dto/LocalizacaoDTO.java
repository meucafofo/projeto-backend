package com.tcc.moradiaestudantil.wsclient.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcc.moradiaestudantil.wsclient.enums.BusinessStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class LocalizacaoDTO {
	@JsonProperty("business_status")
	private BusinessStatus businessStatus;
	
	private GeometryDTO geometry;
	
	private String name;
	
	private List<String> types;
	
	private String vicinity;
}
