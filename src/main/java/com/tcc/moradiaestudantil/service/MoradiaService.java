package com.tcc.moradiaestudantil.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.moradiaestudantil.domain.entity.Comprovante;
import com.tcc.moradiaestudantil.domain.entity.Foto;
import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.dto.DocumentoDTO;
import com.tcc.moradiaestudantil.dto.MoradiaDTO;
import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.dto.response.DetalharMoradiaDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.exception.AuthorizationException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.exception.ResourceNotFoundException;
import com.tcc.moradiaestudantil.repository.FotoRepository;
import com.tcc.moradiaestudantil.repository.MoradiaRepository;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MoradiaService {

	private MoradiaRepository moradiaRepository;

	private UsuarioRepository usuarioRepository;

	private FotoRepository fotoRepository;

	private GenericModelMapper modelMapper;

	private ComprovanteService comprovanteService;

	@Transactional
	public MoradiaDTO cadastrarMoradia(Moradia moradia) {
		var locador = usuarioRepository.findById(UsuarioLogadoService.autheticated().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Locador não encontrado"));
		if (!TipoUsuario.LOCADOR.equals(locador.getTipoUsuario())) {
			throw new AuthorizationException("Usuario não permitido");
		}
		moradia.setLocador(locador);

		return modelMapper.convertEntity(moradiaRepository.save(moradia), MoradiaDTO.class);
	}

	@Transactional
	public void cadastrarComprovante(Long moradiaId, MultipartFile file) {
		try {
			var moradia = moradiaRepository.findById(moradiaId)
					.orElseThrow(() -> new ResourceNotFoundException("Moradia não encontrada!"));

			var comprovante = moradia.getComprovante();

			if (Objects.isNull(comprovante)) {
				comprovante = new Comprovante();
			}

			comprovante.setNome(file.getOriginalFilename());
			comprovante.setContentType(file.getContentType());
			comprovante.setArquivo(file.getBytes());
			comprovante.setStatus(Status.PENDENTE);
			comprovante.setMoradia(moradia);

			comprovanteService.salvar(comprovante);
		} catch (IOException e) {
			throw new RegisterException(e);
		}
	}

	@Transactional(readOnly = true)
	public List<Moradia> listarMoradia() {
		return moradiaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<MoradiaDTO> listarMoradiaPorLocador(Usuario usuario) {
		var list = moradiaRepository.findByLocador(usuario);
		return list.stream().map(u -> modelMapper.convertEntity(u, MoradiaDTO.class)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public DetalharMoradiaDTO recuperarMoradia(Long id) {
		var moradia = moradiaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Anuncio não encontrado"));

		var usuario = UsuarioLogadoService.autheticated();
		if (!(TipoUsuario.ADMINISTRADOR.equals(usuario.getTipoUsuario())) && !moradia.getLocador().equals(usuario)) {
			throw new AuthorizationException("Usuario não tem permissão para acessar o recurso");
		}

		var dto = modelMapper.convertEntity(moradia, DetalharMoradiaDTO.class);
		dto.setComprovante(Objects.nonNull(dto.getComprovante())
				? modelMapper.convertEntity(dto.getComprovante(), DocumentoDTO.class)
				: null);
		return dto;
	}

	@Transactional
	public void encerrarMoradia(Long id, Usuario usuario) {
		var moradia = moradiaRepository.findByIdAndLocador(id, usuario)
				.orElseThrow(() -> new ResourceNotFoundException("Moradia não encontrada"));
		moradiaRepository.delete(moradia);
	}

	@Transactional
	public List<MoradiaDTO> recuperarMoradiasAguardandoAprovacao() {
		return moradiaRepository.recuperarMoradiasAguardandoAprovacao().stream()
				.map(moradia -> modelMapper.convertEntity(moradia, MoradiaDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	public DocumentoDTO cadastrarFoto(Long id, MultipartFile file) {
		try {
			var moradia = moradiaRepository.findById(id)
					.orElseThrow(() -> new RegisterException("Moradia não encontrada"));

			Foto foto = new Foto();
			foto.setNome(file.getOriginalFilename());
			foto.setContentType(file.getContentType());
			foto.setArquivo(file.getBytes());
			foto.setMoradia(moradia);
			return modelMapper.convertEntity(fotoRepository.save(foto), DocumentoDTO.class);
		} catch (IOException e) {
			throw new RegisterException(e);
		}
	}

	@Transactional
	public DetalharMoradiaDTO recuperarMoradiaAProvada(Long id) {
		var moradia = moradiaRepository.findByIdMoradiaAprovada(id).orElseThrow(() -> new RegisterException("Moradia não encontrada"));
		var dto = modelMapper.convertEntity(moradia, DetalharMoradiaDTO.class);
		dto.setTelefone(moradia.getLocador().getTelefone());
		return dto;
	}
}
