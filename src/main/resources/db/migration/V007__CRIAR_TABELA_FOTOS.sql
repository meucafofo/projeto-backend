CREATE TABLE fotos(
	id_foto BIGSERIAL PRIMARY KEY,
	nome_arquivo VARCHAR(30) NOT NULL,
	tipo_conteudo VARCHAR(5) NOT NULL,
	conteudo BYTEA NOT NULL,
	id_moradia BIGSERIAL NOT NULL
);

ALTER TABLE fotos ADD CONSTRAINT fk_foto_moradia
FOREIGN KEY(id_moradia) REFERENCES moradias(id_moradia);