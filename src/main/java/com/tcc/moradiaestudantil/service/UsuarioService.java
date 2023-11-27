package com.tcc.moradiaestudantil.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.moradiaestudantil.domain.entity.Documento;
import com.tcc.moradiaestudantil.domain.entity.Solicitacao;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.dto.AtualizarUsuarioDTO;
import com.tcc.moradiaestudantil.dto.ServiceResponseDTO;
import com.tcc.moradiaestudantil.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.dto.mapper.GenericModelMapper;
import com.tcc.moradiaestudantil.dto.response.DetalharUsuarioDTO;
import com.tcc.moradiaestudantil.dto.response.UsuarioAguardandoAprocacaoDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoDocumento;
import com.tcc.moradiaestudantil.enums.TipoSolicitacao;
import com.tcc.moradiaestudantil.exception.ObjectNotFoundException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.DocumentoRepository;
import com.tcc.moradiaestudantil.repository.SolicitacaoRepository;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;
import com.tcc.moradiaestudantil.utils.MessagesPropertiesUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	private DocumentoRepository documentoRepository;

	private PasswordEncoder passwordEncoder;

	private GenericModelMapper modelMapper;

	private SolicitacaoRepository solicitacaoRepository;

	private EmailService emailService;

	@Transactional(readOnly = true)
	public DetalharUsuarioDTO findById(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);

		if (opt.isEmpty()) {
			new ObjectNotFoundException("Usuario não encontrado. ID: " + id);
		}

		return modelMapper.convertEntity(opt.get(), DetalharUsuarioDTO.class);
	}

	@Transactional
	public ServiceResponseDTO cadastarUsuario(UsuarioDTO usuarioDTO) {
		var usuario = modelMapper.convertEntity(usuarioDTO, Usuario.class);
		usuario.setStatus(Status.PENDENTE);
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

		usuarioRepository.save(usuario);

		return new ServiceResponseDTO("Cadastro realizado com sucesso!", true);
	}

	public Usuario verificarAtualizarId(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado. ID: " + id));
	}

	@Transactional
	public void updateUsuario(AtualizarUsuarioDTO usuarioDto) {
		var usuario = usuarioRepository.findById(usuarioDto.getId())
				.orElseThrow(() -> new RegisterException("Usuario não encontrado ID: " + usuarioDto.getId()));
		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setSexo(usuarioDto.getSexo());
		usuario.setTelefone(usuarioDto.getTelefone());
		usuario.setDataNasc(usuarioDto.getDataNasc());
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setCgc(usuarioDto.getCgc());

		usuarioRepository.save(usuario);
	}

	@Transactional
	public ServiceResponseDTO updateSenha(String senhaAntiga, String senhaNova) {
		try {
			var usuario = usuarioRepository.findById(UsuarioLogadoService.autheticated().getId())
					.orElseThrow(() -> new RegisterException("Usuario não encontrado"));
			Boolean isPwdRight = passwordEncoder.matches(senhaAntiga, usuario.getSenha());
			if (isPwdRight) {
				usuario.setSenha(this.passwordEncoder.encode(senhaNova));
				usuarioRepository.save(usuario);
				return new ServiceResponseDTO("Senha atualizada com sucesso!", true);
			} else {
				throw new RegisterException("");
			}
		} catch (RegisterException e) {
			throw new RegisterException("Senha antiga incorreta!");
		}
	}

	public void deleteById(Long id) {
		Usuario atualizarUsuario = verificarAtualizarId(id);
		if (atualizarUsuario != null) {
			usuarioRepository.deleteById(id);
		}
	}

	public String validCpf(String cpf) {

		if (cpf.length() != 11) {
			throw new RegisterException("CPF inválido. Deve conter 11 dígitos.");
		}
		return cpf;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void cadastrarDocumento(Long id, TipoDocumento tipoDocumento, MultipartFile file) {
		try {
			var usuario = usuarioRepository.findById(id)
					.orElseThrow(() -> new RegisterException("Usuario não encontrado"));
			var documentoPivo = documentoRepository.findByUsuarioAndTipoDocumento(id, tipoDocumento).orElse(null);

			if (Objects.isNull(documentoPivo)) {
				documentoPivo = new Documento();
			}

			documentoPivo.setNome(file.getOriginalFilename());
			documentoPivo.setArquivo(file.getBytes());
			documentoPivo.setContentType(file.getContentType());
			documentoPivo.setDataSituacao(LocalDateTime.now());
			documentoPivo.setStatus(Status.PENDENTE);
			documentoPivo.setTipoDocumento(tipoDocumento);
			documentoPivo.setUsuario(usuario);
			documentoRepository.save(documentoPivo);
		} catch (Exception e) {
			throw new RegisterException(e);
		}
	}

	public List<UsuarioAguardandoAprocacaoDTO> recuperarUsuariosAguardandoAprovacao() {
		return usuarioRepository.recuperarUsuariosAguardandoAprovacao().stream()
				.map(usuario -> modelMapper.convertEntity(usuario, UsuarioAguardandoAprocacaoDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public void solicitarRecuperacaoSenha(String email) {
		var usuario = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new RegisterException("Email não cadastrado"));
		var solicitacao = Solicitacao.builder().token(UUID.randomUUID().toString()).usado(false)
				.tipoSolicitacao(TipoSolicitacao.SENHA).usuario(usuario).build();
		solicitacao = solicitacaoRepository.save(solicitacao);
		emailService.sendMessage(email, MessagesPropertiesUtil.getMessage("EMAIL_TITLE01"),
				MessagesPropertiesUtil.getMessage("EMAIL_BODY01", solicitacao.getToken()));
	}

	@Transactional
	public void recuperarSenha(String token, String senha) {
		var solicitacao = solicitacaoRepository
				.findByTokenAndUsadoAndTipoSolicitacao(token, false, TipoSolicitacao.SENHA)
				.orElseThrow(() -> new RegisterException("Token não identificado"));
		var usuario = solicitacao.getUsuario();
		usuario.setSenha(passwordEncoder.encode(senha));
		solicitacaoRepository.save(solicitacao);
		usuarioRepository.save(usuario);
	}

	@Transactional
	public void solicitarCadastroAdministrador(String email) {
		var solicitacao = Solicitacao.builder().token(UUID.randomUUID().toString()).usado(false)
				.tipoSolicitacao(TipoSolicitacao.CADASTRO).build();
		solicitacaoRepository.save(solicitacao);
		emailService.sendMessage(email, MessagesPropertiesUtil.getMessage("EMAIL_TITLE02"),
				MessagesPropertiesUtil.getMessage("EMAIL_BODY02", solicitacao.getToken()));
	}

	@Transactional
	public ServiceResponseDTO cadastrarAdministrador(UsuarioDTO dto, String token) {
		var solicitacao = solicitacaoRepository
				.findByTokenAndUsadoAndTipoSolicitacao(token, false, TipoSolicitacao.CADASTRO)
				.orElseThrow(() -> new RegisterException("Token não identificado"));
		var resultado = this.cadastarUsuario(dto);
		solicitacao.setUsado(true);
		solicitacaoRepository.save(solicitacao);
		return resultado;
	}
}
