CREATE DATABASE biblioteca_personal;

CREATE TABLE libro (
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	codigo VARCHAR(20) NOT NULL,
	titulo VARCHAR(120) NOT NULL,
	autor VARCHAR(100) NOT NULL,
	categoria VARCHAR(50) NOT NULL,
	anio_publicacion INTEGER NOT NULL,
	precio NUMERIC(8,2) NOT NULL,
	disponible BOOLEAN NOT NULL DEFAULT TRUE,
	CONSTRAINT pk_libro PRIMARY KEY (id),
	CONSTRAINT uk_libro_codigo UNIQUE (codigo),
	CONSTRAINT ck_libro_anio CHECK (anio_publicacion > 0),
	CONSTRAINT ck_libro_precio CHECK (precio > 0)
);

SELECT * FROM libro;