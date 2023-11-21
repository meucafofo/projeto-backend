package com.tcc.moradiaestudantil.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.domain.entity.Usuario;

public interface MoradiaRepository extends JpaRepository<Moradia, Long> {

	List<Moradia> findByLocador(Usuario locador);

	Optional<Moradia> findByIdAndLocador(Long id, Usuario usuario);

	@Query(nativeQuery = true, value = "select m.* from moradias m inner join comprovantes c on c.id_moradia = m.id_moradia where c.status = 'PENDENTE'")
	List<Moradia> recuperarMoradiasAguardandoAprovacao();

	@Query(value = "SELECT m FROM Moradia m JOIN FETCH m.comprovante c WHERE c.status = 'APROVADO' AND m.id = :id")
	Optional<Moradia> findByIdMoradiaAprovada(Long id);
	
	
	
	@Query(nativeQuery = true, value = "select m.*, coord.latitude, coord.longitude from moradias m "
			+ "inner join comprovantes c on c.id_moradia = m.id_moradia "
			+ "inner join coordenadas coord on coord.id_coordenada = m.id_coordenada "
			+ "where c.status = 'APROVADO' and (?1 in (select 'todos') "
			+ "	or m.tipo_de_moradia = ?1) and m.preco > ?2 and m.preco < ?3")
	List<Moradia> pesquisarMoradiaPelosParametros(String tipoMoradia, BigDecimal precoMinimo, BigDecimal precoMaximo);
}
