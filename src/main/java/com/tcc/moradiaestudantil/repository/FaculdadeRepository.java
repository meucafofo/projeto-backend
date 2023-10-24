package com.tcc.moradiaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.moradiaestudantil.domain.entity.Faculdade;

public interface FaculdadeRepository extends JpaRepository<Faculdade, Long> {
}
