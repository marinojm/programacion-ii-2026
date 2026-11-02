SELECT * FROM salon;

SELECT id, nombre, ubicacion, capacidad, tarifa_por_hora, activo 
FROM salon;

SELECT id, cliente_nombre, fecha_reserva, horas_reservadas, cantidad_invitados, estado, salon_id 
FROM reserva;

SELECT r.id AS reserva_id, 
       r.cliente_nombre, 
       s.nombre AS salon_nombre, 
       r.fecha_reserva, 
       r.cantidad_invitados, 
       r.estado
FROM reserva r
JOIN salon s ON r.salon_id = s.id;

