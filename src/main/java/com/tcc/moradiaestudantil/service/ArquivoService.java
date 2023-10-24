package com.tcc.moradiaestudantil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.entity.Comprovante;
import com.tcc.moradiaestudantil.domain.entity.Documento;
import com.tcc.moradiaestudantil.domain.entity.Foto;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.exception.AuthorizationException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.ComprovanteRepository;
import com.tcc.moradiaestudantil.repository.DocumentoRepository;
import com.tcc.moradiaestudantil.repository.FotoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArquivoService {
	private DocumentoRepository documentoRepository;
	private ComprovanteRepository comprovanteRepository;
	private FotoRepository fotoRepository;

	@Transactional(readOnly = true)
	public Documento recuperarDocumento(Long idDocumento) {
		var documento = documentoRepository.findById(idDocumento)
				.orElseThrow(() -> new RegisterException("Documento não encontrado"));

		var usuario = UsuarioLogadoService.autheticated();

		if (!(TipoUsuario.ADMINISTRADOR.equals(usuario.getTipoUsuario())) && !documento.getUsuario().equals(usuario)) {
			throw new AuthorizationException("Usuario não tem permissão para acessar o recurso!");
		}

		return documento;
	}

	@Transactional(readOnly = true)
	public Comprovante recuperarComprovante(Long idComprovante) {
		var comprovante = comprovanteRepository.findById(idComprovante)
				.orElseThrow(() -> new RegisterException("Comprovante não encontrado"));
		var usuario = UsuarioLogadoService.autheticated();
		if (!(TipoUsuario.ADMINISTRADOR.equals(usuario.getTipoUsuario()))
				&& !comprovante.getMoradia().getLocador().equals(usuario)) {
			throw new AuthorizationException("Usuario não tem permissão para acessar o recurso!");
		}

		return comprovante;
	}

	@Transactional
	public Foto recuperarFoto(Long id) {
		return fotoRepository.findById(id).orElseThrow(() -> new RegisterException("Foto não encontrada"));
	}

	@Transactional
	public void excluirFoto(Long id, Usuario autheticated) {
		var foto = fotoRepository.findById(id).orElseThrow(() -> new RegisterException("Foto não encontrada"));
		if (!foto.getMoradia().getLocador().equals(autheticated)) {
			throw new AuthorizationException("Usuario não tem permissão para excluir o recurso");
		}
		fotoRepository.delete(foto);
	}
}
