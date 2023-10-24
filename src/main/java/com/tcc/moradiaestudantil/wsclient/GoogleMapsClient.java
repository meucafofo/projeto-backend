package com.tcc.moradiaestudantil.wsclient;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcc.moradiaestudantil.wsclient.dto.GoogleMapsDTO;

@Component
public class GoogleMapsClient {

	@Value("${google.maps.api.url}")
	private String url;

	@Value("${google.maps.api.key}")
	private String apiKey;

	public GoogleMapsDTO pesquisarLocalizacoes(final double latitude, final double longitude,
			final String keyword, final int radius, final String next) {

		var uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		uriBuilder.path("/maps/api/place/nearbysearch/json").queryParam("keyword", keyword).queryParam("key", apiKey)
				.queryParam("location", latitude + "," + longitude).queryParam("radius", radius).queryParam("language", "pt-BR");

		if (Objects.nonNull(next)) {
			uriBuilder.queryParam("pagetoken", next);
		}

		var restTemplate = new RestTemplate();

		var dto = restTemplate.getForEntity(uriBuilder.build().toUri(), GoogleMapsDTO.class).getBody();

		return dto;
	}
	
	public GoogleMapsDTO pesquisarEndereco(String query) {
		var uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		uriBuilder.path("/maps/api/place/textsearch/json").queryParam("query", query).queryParam("key", apiKey)				
				.queryParam("language", "pt-BR");

		var restTemplate = new RestTemplate();

		var dto = restTemplate.getForEntity(uriBuilder.build().toUri(), GoogleMapsDTO.class).getBody();

		return dto;		
	}
}
