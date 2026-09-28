SELECT codigo, nombre, categoria, precio, stock
FROM producto;

SELECT id, codigo, nombre, categoria, precio, stock
FROM producto
WHERE categoria = 'Tecnologia';

SELECT codigo, nombre, categoria, precio, stock
FROM producto
ORDER BY precio DESC;

SELECT codigo, nombre, categoria, precio, stock
FROM producto
WHERE stock <= 3;

SELECT codigo, nombre, categoria, precio, stock, activo
FROM producto
WHERE activo = true AND stock > 0;

SELECT id, codigo, nombre, categoria, precio, stock, activo
FROM producto
WHERE activo = true AND stock > 0;

SELECT id, codigo, nombre, categoria, precio, stock, activo
FROM producto
WHERE codigo = 'TEC-001';

--ACTUALIZACIONES Y ELIMINACION
--SIMULACION DE UNA VENTA

--Comprobar estado antes de la venta
SELECT codigo, nombre, stock FROM producto WHERE codigo = 'TEC-001';

UPDATE producto
SET stock = stock - 2
WHERE codigo = 'TEC-001';

--comprobar estado despue de la venta
SELECT codigo, nombre, stock FROM producto WHERE codigo = 'TEC-001';

--PRODUCTOS AGOTADOS
--consultar productos agotados antes de actualizar
SELECT codigo, nombre, stock, activo FROM producto WHERE stock = 0;

--actualizar estado de agotados
UPDATE producto
SET activo = false
WHERE stock = 0;

--consultar productos inactivos para verificar el cambio
SELECT codigo, nombre, stock, activo FROM producto WHERE activo = false;

--ELIMINACION DEL REGISTRO TEMPORAL
--Comprobar que el producto exista antes de eliminar
SELECT id, codigo, nombre, categoria FROM producto WHERE codigo = 'TEMP-001';

--Eliminar el registro
DELETE FROM producto WHERE codigo = 'TEMP-001';

--Comprobar que ya no se encuentra almacenado
SELECT id, codigo, nombre, categoria FROM producto WHERE codigo = 'TEMP-001';