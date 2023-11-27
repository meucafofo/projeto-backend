CREATE TABLE solicitacoes(
	id_solicitacao BIGSERIAL PRIMARY KEY,
	token VARCHAR(36) NOT NULL,
	tipo_solicitacao VARCHAR(8),
	usado boolean DEFAULT false,
	id_usuario_solicitado BIGINT NULL
);

ALTER TABLE solicitacoes ADD CONSTRAINT fk_solicitacao_usuario
FOREIGN KEY(id_usuario_solicitado) REFERENCES usuarios(id_usuario);