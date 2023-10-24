package com.tcc.moradiaestudantil.dto.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String token;
	
	public LoginResponseDTO(String token) {
		this.token = token;
	}
}
