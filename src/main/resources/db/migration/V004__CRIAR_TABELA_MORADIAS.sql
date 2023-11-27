CREATE TABLE moradias (
	id_moradia BIGSERIAL PRIMARY KEY,
	endereco VARCHAR(180) NOT NULL,
	bairro VARCHAR(30) NOT NULL,
	cep VARCHAR(9) NOT NULL,
	descricao VARCHAR(180),
	preco NUMERIC(12, 2) NOT NULL,
	tipo_de_moradia VARCHAR(11) NOT NULL,
	id_locador BIGSERIAL NOT NULL,
	id_coordenada BIGINT NULL
);

ALTER TABLE moradias ADD CONSTRAINT fk_moradia_locador
FOREIGN KEY(id_locador) REFERENCES usuarios(id_usuario);

ALTER TABLE moradias ADD CONSTRAINT fk_moradia_coordenada
FOREIGN KEY(id_coordenada) REFERENCES coordenadas(id_coordenada);