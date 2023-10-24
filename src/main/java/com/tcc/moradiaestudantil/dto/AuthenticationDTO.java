package com.tcc.moradiaestudantil.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
}
