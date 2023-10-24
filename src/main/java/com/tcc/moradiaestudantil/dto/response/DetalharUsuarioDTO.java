package com.tcc.moradiaestudantil.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.tcc.moradiaestudantil.dto.DocumentoDTO;
import com.tcc.moradiaestudantil.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalharUsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String cgc;
	private String dataNasc;
	private String sexo;
	private String email;
	private String senha;
	private String telefone;
	private TipoUsuario tipoUsuario;
	private List<DocumentoDTO> documentos = new ArrayList<>();
}
