CREATE TABLE salon (
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	nombre VARCHAR(100) NOT NULL,
	ubicacion VARCHAR(150) NOT NULL,
	capacidad INTEGER NOT NULL,
	tarifa_por_hora NUMERIC(10,2) NOT NULL,
	activo BOOLEAN NOT NULL DEFAULT TRUE,
	CONSTRAINT pk_salon PRIMARY KEY (id),
	CONSTRAINT uk_salon_nombre UNIQUE (nombre),
	CONSTRAINT ck_salon_capacidad CHECK (capacidad > 0),
	CONSTRAINT ck_salon_tarifa CHECK (tarifa_por_hora > 0)
);

CREATE TABLE reserva (
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	cliente_nombre VARCHAR(120) NOT NULL,
	fecha_reserva DATE NOT NULL,
	horas_reservadas INTEGER NOT NULL,
	cantidad_invitados INTEGER NOT NULL,
	costo_total NUMERIC(10,2) NOT NULL,
	estado VARCHAR(30) NOT NULL DEFAULT 'CONFIRMADA',
	salon_id BIGINT NOT NULL,
	CONSTRAINT pk_reserva PRIMARY KEY (id),
	CONSTRAINT fk_reserva_salon FOREIGN KEY (salon_id) REFERENCES salon(id) ON DELETE CASCADE,
	CONSTRAINT ck_reserva_horas CHECK (horas_reservadas > 0),
	CONSTRAINT ck_reerva_invitados CHECK (cantidad_invitados > 0)
);

--insert de 4 salones de prueba
INSERT INTO salon (nombre, ubicacion, capacidad, tarifa_por_hora, activo) VALUES
('Salón Imperial', 'Nivel 1 - Ala Norte', 150, 250.00, true),
('Salón Cristal', 'Nivel 2 - Ala Sur', 80, 150.00, true),
('Gran Jardín Bellavista', 'Área Verde Exterior', 300, 400.00, true),
('Salón Ejecutivo VIP', 'Nivel 3 - Torre Central', 40, 100.00, true);

--Insert de 10 reservas de prueba
INSERT INTO reserva (cliente_nombre, fecha_reserva, horas_reservadas, cantidad_invitados, costo_total, estado, salon_id) VALUES
('Corporación Alfa S.A.', '2026-11-10', 4, 120, 1000.00, 'CONFIRMADA', 1),
('María Fernandez', '2026-11-12', 5, 60, 750.00, 'CONFIRMADA', 2),
('Boda Gómez-Pérez', '2026-11-15', 8, 250, 3200.00, 'CONFIRMADA', 3),
('Tech Solutions LLC', '2026-11-18', 3, 30, 300.00, 'CONFIRMADA', 4),
('Aniversario Grupo Beta', '2026-11-20', 6, 140, 1500.00, 'CONFIRMADA', 1),
('Graduación Colegio Central', '2026-11-22', 7, 280, 2800.00, 'CONFIRMADA', 3),
('Seminario de Finanzas', '2026-11-25', 4, 75, 600.00, 'CONFIRMADA', 2),
('Taller de Liderazgo', '2026-11-28', 5, 25, 500.00, 'CONFIRMADA', 4),
('Cena de Fin de Año - Delta', '2026-12-01', 6, 110, 1500.00, 'CONFIRMADA', 1),
('Cumpleaños Infantil López', '2026-12-05', 4, 50, 600.00, 'CONFIRMADA', 2);