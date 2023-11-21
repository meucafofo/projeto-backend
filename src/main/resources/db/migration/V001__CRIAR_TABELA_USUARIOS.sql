CREATE TABLE usuarios (
	id_usuario BIGSERIAL PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	telefone VARCHAR(18) UNIQUE,
	email VARCHAR(60) NOT NULL UNIQUE,
	senha VARCHAR(60) NOT NULL,
	data_nasc DATE NOT NULL,
	cgc VARCHAR(18) UNIQUE,
	cpf VARCHAR(14) UNIQUE,
	sexo VARCHAR(9) NOT NULL,
	status_usuario VARCHAR(8),
	role_usuario VARCHAR(13)
);