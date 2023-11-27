CREATE TABLE faculdades(
	id_faculdade BIGSERIAL PRIMARY KEY,
	nome VARCHAR(180) NOT NULL,
	localidade VARCHAR(180),
	id_coordenada BIGSERIAL NOT NULL
);

ALTER TABLE faculdades ADD CONSTRAINT fk_faculdade_coordenada
FOREIGN KEY(id_coordenada) REFERENCES coordenadas(id_coordenada);