package com.tcc.moradiaestudantil.dto.response;

import java.util.List;

import com.tcc.moradiaestudantil.dto.DocumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalharMoradiaDTO {
	private Integer id;
	private String endereco;
	private String bairro;
	private String cep;
	private String descricao;
	private Double preco;
	private String tipoMoradia;
	private DocumentoDTO comprovante;
	private List<DocumentoDTO> fotos;
	private String telefone;
}
