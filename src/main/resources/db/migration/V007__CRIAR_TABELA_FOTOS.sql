CREATE TABLE fotos(
	id_foto BIGSERIAL PRIMARY KEY,
	nome_arquivo VARCHAR(180) NOT NULL,
	tipo_conteudo VARCHAR(16) NOT NULL,
	conteudo BIGINT NOT NULL,
	id_moradia BIGSERIAL NOT NULL
);

ALTER TABLE fotos ADD CONSTRAINT fk_foto_moradia
FOREIGN KEY(id_moradia) REFERENCES moradias(id_moradia);