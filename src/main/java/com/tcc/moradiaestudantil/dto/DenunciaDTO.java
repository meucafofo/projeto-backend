package com.tcc.moradiaestudantil.dto;

import java.io.Serializable;

import com.tcc.moradiaestudantil.domain.entity.Denuncia;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoDenuncia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private TipoDenuncia motivo;

    private Status status;

	public DenunciaDTO(Denuncia d) {
		this.id = d.getId();
		this.motivo = d.getTipoDenuncia();
		this.status = d.getStatus();
	}
    
    
}
