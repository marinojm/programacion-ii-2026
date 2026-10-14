CREATE TABLE mascota (
id BIGINT GENERATED ALWAYS AS IDENTITY,
codigo VARCHAR(20) NOT NULL,
nombre VARCHAR(80) NOT NULL,
especie VARCHAR(30) NOT NULL,
edad_meses INTEGER NOT NULL,
peso NUMERIC(6,2) NOT NULL,
activa BOOLEAN NOT NULL DEFAULT TRUE,

CONSTRAINT pk_mascota PRIMARY KEY (id),

CONSTRAINT uk_mascota_codigo UNIQUE (codigo),

CONSTRAINT ck_mascota_edad CHECK (edad_meses >= 0),

CONSTRAINT ck_mascota_peso CHECK (peso > 0)

);

INSERT INTO mascota (codigo, nombre, especie, edad_meses, peso, activa)
VALUES
('VET-01', 'Firulais', 'Canino', 24, 15.50, TRUE),
('VET-02', 'Michi', 'Felino', 12, 4.20, TRUE),
('VET-03', 'Roco', 'Canino', 60, 28.00, TRUE);