package com.tcc.moradiaestudantil.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.moradiaestudantil.domain.entity.Agendamento;
import com.tcc.moradiaestudantil.domain.entity.Comprovante;
import com.tcc.moradiaestudantil.domain.entity.Foto;
import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.dto.DocumentoDTO;
import com.tcc.moradiaestudantil.dto.MoradiaDTO;
import com.tcc.moradiaestudantil.dto.ServiceResponseDTO;
import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.dto.response.DetalharAgendamentoDTO;
import com.tcc.moradiaestudantil.dto.response.DetalharMoradiaDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.StatusAgendamento;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.exception.AuthorizationException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.exception.ResourceNotFoundException;
import com.tcc.moradiaestudantil.repository.AgendamentoRepository;
import com.tcc.moradiaestudantil.repository.FotoRepository;
import com.tcc.moradiaestudantil.repository.MoradiaRepository;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;
import com.tcc.moradiaestudantil.utils.MessagesPropertiesUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MoradiaService {

	private MoradiaRepository moradiaRepository;

	private UsuarioRepository usuarioRepository;

	private AgendamentoRepository agendamentoRepository;

	private FotoRepository fotoRepository;

	private GenericModelMapper modelMapper;

	private ComprovanteService comprovanteService;

	private EmailService emailService;

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
		var moradia = moradiaRepository.findByIdMoradiaAprovada(id)
				.orElseThrow(() -> new RegisterException("Moradia não encontrada"));
		var dto = modelMapper.convertEntity(moradia, DetalharMoradiaDTO.class);
		dto.setTelefone(moradia.getLocador().getTelefone());
		return dto;
	}

	@Transactional
	public ServiceResponseDTO agendarVisita(Long idMoradia, String emailAluno, LocalDateTime dataVisita) {
		var aluno = usuarioRepository.findByEmailAndStatus(emailAluno, Status.APROVADO)
				.orElseThrow(() -> new RegisterException("Usuario aprovado não identificado"));
		var moradia = moradiaRepository.findByIdMoradiaAprovada(idMoradia)
				.orElseThrow(() -> new RegisterException("Moradia não encontrada"));
		var agendamento = Agendamento.builder().dataVisita(dataVisita).moradia(moradia).usuario(aluno).build();
		agendamentoRepository.save(agendamento);

		return ServiceResponseDTO.builder().message("Agendamento realizado!").status(true).build();
	}

	@Transactional
	public List<DetalharAgendamentoDTO> recuperarAgendamentos(Long idLocador) {

		var agendamentos = agendamentoRepository.findByUsuarioLocadorId(idLocador).stream().map(agendamento -> {
			var dto = modelMapper.convertEntity(agendamento, DetalharAgendamentoDTO.class);
			dto.setEmail(agendamento.getUsuario().getEmail());

			return dto;
		}).collect(Collectors.toList());

		return agendamentos;
	}

	@Transactional
	public ServiceResponseDTO atualizarStatusAgendamento(Long idAgendamento, StatusAgendamento status) {
		var agendamento = agendamentoRepository.findById(idAgendamento)
				.orElseThrow(() -> new RegisterException("Agendamento não encontrado"));
		agendamento.setStatusAgendamento(status);
		agendamentoRepository.save(agendamento);
		
		new Thread(() -> emailService.sendMessage(agendamento.getUsuario().getEmail(),
				StatusAgendamento.CANCELADO.equals(status) ? MessagesPropertiesUtil.getMessage("EMAIL_TITLE04")
						: MessagesPropertiesUtil.getMessage("EMAIL_TITLE03"),
				StatusAgendamento.CANCELADO.equals(status)
						? MessagesPropertiesUtil.getMessage("EMAIL_BODY04", agendamento.getUsuario().getNome(),
								agendamento.getMoradia().getEndereco())
						: MessagesPropertiesUtil.getMessage("EMAIL_BODY03", agendamento.getUsuario().getNome(),
								agendamento.getMoradia().getEndereco(), agendamento.getDataVisita().toString()))).start();

		return ServiceResponseDTO.builder().message("Agendamento " + status.getStatusName()).status(true).build();
	}
}
