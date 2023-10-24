package com.tcc.moradiaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.moradiaestudantil.domain.entity.Comprovante;

public interface ComprovanteRepository extends JpaRepository<Comprovante, Long> {
}
