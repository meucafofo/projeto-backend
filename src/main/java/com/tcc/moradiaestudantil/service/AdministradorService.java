package com.tcc.moradiaestudantil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.entity.Coordenada;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.DocumentoRepository;
import com.tcc.moradiaestudantil.repository.MoradiaRepository;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;
import com.tcc.moradiaestudantil.wsclient.GoogleMapsClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdministradorService {
	private MoradiaRepository moradiaRepository;
	private DocumentoRepository documentoRepository;
	private GoogleMapsClient mapsClient;
	private UsuarioRepository usuarioRepository;

	@Transactional
	public void aprovarMoradia(Long id, Status status) {
		var moradia = moradiaRepository.findById(id).orElseThrow(() -> new RegisterException("Moradia não encontrada"));
		moradia.getComprovante().setStatus(status);

		if (Status.APROVADO.equals(status)) {
			var endereco = moradia.getEndereco() + " - " + moradia.getBairro() + ", Brasília - DF, " + moradia.getCep();
			var geoLocalizacao = mapsClient.pesquisarEndereco(endereco);

			if (geoLocalizacao.getResults().isEmpty()) {
				throw new RegisterException("O serviço do Google Maps não localizou o endereço");
			}
			var location = geoLocalizacao.getResults().get(0).getGeometry().getLocation();
			moradia.setCoordenada(new Coordenada(null, location.getLat(), location.getLng()));
		}

		moradiaRepository.save(moradia);
	}

	@Transactional
	public void aprovarDocumento(Long id, Status status) {
		var documento = documentoRepository.findById(id)
				.orElseThrow(() -> new RegisterException("Documento não encontrado"));
		documento.setStatus(status);
		documento = documentoRepository.save(documento);

		var usuario = documento.getUsuario();

		usuario.setStatus(usuario.getDocumentos().stream().allMatch(
				documentoUsu -> Status.APROVADO.equals(documentoUsu.getStatus())) ? Status.APROVADO : Status.PENDENTE);
		usuarioRepository.save(usuario);
	}
}
