package com.tcc.moradiaestudantil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.moradiaestudantil.domain.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	
	@Query(nativeQuery = true, value = "SELECT a.*, u2.* FROM usuarios u "
			+ "INNER JOIN moradias m ON u.id_usuario = m.id_locador "
			+ "INNER JOIN coordenadas c ON c.id_coordenada = m.id_coordenada "
			+ "INNER JOIN comprovantes c2 ON c2.id_moradia = m.id_moradia and c2.status_comprovante = 'APROVADO' "
			+ "INNER JOIN agendamentos a ON a.id_moradia = m.id_moradia "
			+ "INNER JOIN usuarios u2 ON u2.id_usuario = a.id_aluno "
			+ "WHERE u.id_usuario = ? AND u.role_usuario IN ('LOCADOR', 'CONFIRMADO') "
			+ "AND EXTRACT(year FROM a.data_hora) = EXTRACT(year FROM CURRENT_DATE)")
	public List<Agendamento> findByUsuarioLocadorId(Long id);
}
