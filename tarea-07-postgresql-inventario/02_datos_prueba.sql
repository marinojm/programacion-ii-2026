-- Registro de 8 productos validos
INSERT INTO producto 
(codigo, nombre, categoria, precio, stock, activo) VALUES
('TEC-001', 'Teclado Mecánico RGB', 'Tecnología', 350.00, 15, true),
('TEC-002', 'Mouse Inalamrico', 'Tecnología', 120.50, 2, true),
('HOG-001', 'Cafetera', 'Hogar', 450.00, 8, true),
('HOG-002', 'Licuadora', 'Hogar', 299.00, 0, true),
('OFI-001', 'Silla Ejecutiva', 'Oficina', 850.00, 5, true),
('OFI-002', 'Escritorio Ajustable', 'Oficina', 1250.00, 4, true),
('TEC-003', 'Monitor 27 ´pulgadas', 'Tecnologia', 1800.00, 10, true);

--Omitimos la columna activo en el 8vo producto (true automaticamente)
INSERT INTO producto (codigo, nombre, categoria, precio, stock) VALUES
('HOG-003', 'Juego de Sartenes', 'Hogar', 180.00, 12);

--Registro de producto temporal para prueba de eliminacion
INSERT INTO producto (codigo, nombre, categoria, precio, stock) VALUES
('TEMP-001', 'Producto Temporal de Prueba', 'Pruebas', 10.00, 1);

--COMPROBACION INTENCIONAL DE ERRORES/RESTRICCIONES
--ERROR 1 código duplicado debe rechazarlo uk_producto_codigo
INSERT INTO producto (codigo, nombre, categoria, precio, stock)
VALUES ('TEC-004', 'Audifonos', 'Tecnologia', 250.00, 5);

--CORRECCIÓN ERROR 1 
INSERT INTO producto (codigo, nombre, categoria, precio, stock)
VALUES ('TEC-004', 'Audifonos', 'Tecnologia', 250.00, 5);

--ERROR 2 stock negativo debe rechazarlo ck_producto_stock
INSERT INTO producto (codigo, nombre, categoria, precio, stock)
VALUES ('OFI-003', 'Organizador de escritorio', 'Oficina', 45.00, -5);

--CORRECION ERROR 2 el stock  un entero no negativo
INSERT INTO producto (codigo, nombre, categoria, precio, stock)
VALUES ('OFI-003', 'Organizador de escritorio', 'oficina',45.00, 10);
