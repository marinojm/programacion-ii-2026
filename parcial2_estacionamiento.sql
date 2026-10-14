CREATE DATABASE estacionamiento_db;


CREATE TABLE IF NOT EXISTS vehiculos (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    propietario VARCHAR(100) NOT NULL,
    hora_ingreso VARCHAR(10) NOT NULL,
    horas_utilizadas NUMERIC(5,2) NOT NULL,
    costo_total NUMERIC(8,2) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);