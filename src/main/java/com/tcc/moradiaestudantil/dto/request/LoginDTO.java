package com.tcc.moradiaestudantil.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	
	public LoginDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
}
