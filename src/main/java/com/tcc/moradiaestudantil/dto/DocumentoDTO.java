package com.tcc.moradiaestudantil.dto;

import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
	private Long id;
	private String nome;
	private TipoDocumento tipoDocumento;
	private Status status;
}
