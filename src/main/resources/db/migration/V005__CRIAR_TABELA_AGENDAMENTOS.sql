CREATE TABLE agendamentos(
	id_agenda BIGSERIAL PRIMARY KEY,
	data_hora TIMESTAMP NOT NULL,
	id_aluno BIGSERIAL NOT NULL,
	id_moradia BIGSERIAL NOT NULL
);

ALTER TABLE agendamentos ADD CONSTRAINT fk_agendamento_aluno
FOREIGN KEY(id_aluno) REFERENCES usuarios(id_usuario);

ALTER TABLE agendamentos ADD CONSTRAINT fk_agendamento_moradia
FOREIGN KEY(id_moradia) REFERENCES moradias(id_moradia);