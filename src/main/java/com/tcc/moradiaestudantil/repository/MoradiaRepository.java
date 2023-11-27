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

	@Query(nativeQuery = true, value = "SELECT m.* FROM moradias m INNER JOIN comprovantes c on c.id_moradia = m.id_moradia "
			+ "WHERE c.status_comprovante = 'PENDENTE'")
	List<Moradia> recuperarMoradiasAguardandoAprovacao();

	@Query(value = "SELECT m FROM Moradia m JOIN FETCH m.comprovante c "
			+ "WHERE c.status = 'APROVADO' AND m.id = :id")
	Optional<Moradia> findByIdMoradiaAprovada(Long id);
	
	@Query(nativeQuery = true, value = "SELECT m.*, coord.latitude, coord.longitude FROM moradias m "
			+ "INNER JOIN comprovantes c ON c.id_moradia = m.id_moradia "
			+ "INNER JOIN coordenadas coord ON coord.id_coordenada = m.id_coordenada "
			+ "WHERE c.status_comprovante = 'APROVADO' AND (?1 in (select 'todos') "
			+ "  OR m.tipo_de_moradia = ?1) AND m.preco > ?2 AND m.preco < ?3")
	List<Moradia> pesquisarMoradiaPelosParametros(String tipoMoradia, BigDecimal precoMinimo, BigDecimal precoMaximo);
}
