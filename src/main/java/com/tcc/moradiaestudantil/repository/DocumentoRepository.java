package com.tcc.moradiaestudantil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.moradiaestudantil.domain.entity.Documento;
import com.tcc.moradiaestudantil.enums.TipoDocumento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{
	@Query("SELECT d FROM Documento d JOIN FETCH d.usuario u WHERE u.id=:id AND d.tipoDocumento=:tipoDocumento")
	Optional<Documento> findByUsuarioAndTipoDocumento(Long id, TipoDocumento tipoDocumento);

	@Query("SELECT d FROM Documento d JOIN FETCH d.usuario u WHERE d.id=:idDocumento")
	Optional<Documento> findByIdAndUsuario(Long idDocumento);
	
	@Query("SELECT d from Documento d JOIN FETCH d.usuario u WHERE d.status='PENDENTE'")
	List<Documento> findDocumentosAguardandoAprovacao();
}
