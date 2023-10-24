package com.tcc.moradiaestudantil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.moradiaestudantil.domain.entity.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
	Optional<Solicitacao> findByTokenAndUsado(String token, Boolean usado);
}
