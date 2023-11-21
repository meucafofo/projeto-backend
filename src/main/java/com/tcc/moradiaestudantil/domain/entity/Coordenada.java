package com.tcc.moradiaestudantil.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coordenadas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordenada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coordenada")
	private Long id;
	private Double latitude;
	private Double longitude;
}
