package com.tcc.moradiaestudantil.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoDenuncia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name="Denuncia")
@AllArgsConstructor
@NoArgsConstructor
public class Denuncia implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data_hora")
	private LocalDateTime horario;
    
    private Integer motivo;
    
    private String descricao;

    private Integer status;

    // Relacionamentos
    
    @ManyToOne
    @JoinColumn(name = "id_acusador")
    private Usuario acusador;
    
    @ManyToOne
    @JoinColumn(name = "id_alvo")
    private Usuario alvo;

	public Denuncia(LocalDateTime horario, TipoDenuncia motivo, String descricao, Status status, Usuario acusador,
			Usuario alvo) {
		super();
		this.horario = horario;
		this.motivo = motivo.getCodigo();
		this.descricao = descricao;
		this.status = Status.PENDENTE.getCodigo();
		this.acusador = acusador;
		this.alvo = alvo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public TipoDenuncia getTipoDenuncia() {
		return TipoDenuncia.toEnum(motivo);
	}

	public void setTipoDenuncia(TipoDenuncia tipo) {
		this.motivo = tipo.getCodigo();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCodigo();
	}

	public Usuario getAcusador() {
		return acusador;
	}

	public void setAcusador(Usuario acusador) {
		this.acusador = acusador;
	}

	public Usuario getAlvo() {
		return alvo;
	}

	public void setAlvo(Usuario alvo) {
		this.alvo = alvo;
	}
}
