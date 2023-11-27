package com.tcc.moradiaestudantil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.moradiaestudantil.domain.entity.Solicitacao;
import com.tcc.moradiaestudantil.enums.TipoSolicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
	Optional<Solicitacao> findByTokenAndUsadoAndTipoSolicitacao(String token, Boolean usado, TipoSolicitacao tipoSolicitacao);
}
