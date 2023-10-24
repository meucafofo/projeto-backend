package com.tcc.moradiaestudantil.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name="usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	@EqualsAndHashCode.Include
	protected Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf", unique = true)
	private String cpf;
	
	@Column(name = "cgc", unique = true)
	private String cgc;
	
	@Column(name = "data_nascimento")
	private String dataNasc;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Email
	@Column(name = "email", unique = true)
	@NotBlank
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "tipo_usuario")
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "acusador")
    private List<Denuncia> denunciasAcusador = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "alvo")
    private List<Denuncia> denunciasAlvo = new ArrayList<>();
    
    @JsonIgnore
	@OneToMany(mappedBy = "locador")
 	private List<Moradia> moradia;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.tipoUsuario == TipoUsuario.ADMINISTRADOR) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLER_USER"));
		}
		else return List.of(new SimpleGrantedAuthority("ROLER_USER"));
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
