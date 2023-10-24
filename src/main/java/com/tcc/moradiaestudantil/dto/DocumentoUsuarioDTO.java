package com.tcc.moradiaestudantil.dto;

import com.tcc.moradiaestudantil.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentoUsuarioDTO {
	private Long id;
	private String nomeUsuario;
	private TipoDocumento tipoDocumento;
}
