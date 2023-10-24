package com.tcc.moradiaestudantil.wsclient.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class GoogleMapsDTO {
	@JsonProperty("next_page_token")
	private String nextPageToken;
	
	private List<LocalizacaoDTO> results;
	
	private String status;
}
