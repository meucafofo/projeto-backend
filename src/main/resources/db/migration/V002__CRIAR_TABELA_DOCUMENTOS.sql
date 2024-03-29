CREATE TABLE documentos (
   id_documento BIGSERIAL PRIMARY KEY,
   nome_arquivo VARCHAR (180) NOT NULL,
   tipo_conteudo VARCHAR (11) NOT NULL,
   data_sit TIMESTAMP NOT NULL,
   tipo_documento VARCHAR (16),
   status_documento VARCHAR(8),
   conteudo BIGINT NOT NULL,
   id_usuario BIGSERIAL
);

ALTER TABLE documentos ADD CONSTRAINT fk_documento_usuario 
FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario);