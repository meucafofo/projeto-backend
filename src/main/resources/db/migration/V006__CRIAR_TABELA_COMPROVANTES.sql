CREATE TABLE comprovantes(
	id_comprovante BIGSERIAL PRIMARY KEY,
	nome_arquivo VARCHAR (180) NOT NULL,
	tipo_conteudo VARCHAR (16) NOT NULL,
	data_sit TIMESTAMP NOT NULL,
	status_comprovante VARCHAR(11),
   	conteudo BIGINT NOT NULL,
   	id_moradia BIGSERIAL NOT NULL
);

ALTER TABLE comprovantes ADD CONSTRAINT fk_comprovante_moradia
FOREIGN KEY(id_moradia) REFERENCES moradias(id_moradia)