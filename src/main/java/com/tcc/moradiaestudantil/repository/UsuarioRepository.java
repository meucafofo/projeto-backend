package com.tcc.moradiaestudantil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.Status;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findOneByEmailAndSenha(String email, String encodePassword);

	Optional<Usuario> findByCpf(String cpf);

	Optional<Usuario> findByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT * FROM usuarios u WHERE u.role_usuario = 'ADMINISTRADOR' FETCH FIRST 1 ROW ONLY")
	Optional<Usuario> recuperarUsuarioSystema();

	@Query(nativeQuery = true, value = "SELECT DISTINCT u.* from usuarios u WHERE NOT u.role_usuario = 'ADMINISTRADOR' "
			+ "  AND u.status_usuario = 'PENDENTE' "
			+ "  AND ((u.role_usuario = 'LOCADOR' AND EXISTS (SELECT 1 FROM documentos d WHERE d.id_usuario = u.id_usuario)) "
			+ "  OR (u.role_usuario = 'ALUNO' AND (SELECT COUNT(1) FROM documentos d2 WHERE d2.id_usuario = u.id_usuario) > 1 ))")
	List<Usuario> recuperarUsuariosAguardandoAprovacao();

	Optional<Usuario> findByEmailAndStatus(String email, Status status);
}
