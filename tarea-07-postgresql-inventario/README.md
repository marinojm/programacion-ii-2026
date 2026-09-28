# Control de Inventario con PostgreSQL

## Descripción
Proyecto de diseño e implementación de una base de datos en PostgreSQL para el control de inventario básico de una tienda. Aplica la selección adecuada de tipos de datos, llaves primarias autocorrelativas, restricciones (`UNIQUE`, `CHECK`, `NOT NULL`, `DEFAULT`) y operaciones CRUD mediante instrucciones SQL puras.

## Estudiante
* **Nombre completo:** Marino Jeriel Cabrera Mendoza
* **Carné:** 9941-23-8505

## Datos de la Base de Datos
* **Nombre de la base de datos:** `control_inventario`
* **Tabla principal:** `producto`

## Requisitos de Ejecución
1. PostgreSQL 12 o superior instalado.
2. Un cliente SQL como **pgAdmin 4** o la consola `psql`.

## Orden de Ejecución de Scripts SQL
Para desplegar correctamente la base de datos, ejecute los archivos de la carpeta `database/` en el siguiente orden secuencial:

1. **`01_creacion.sql`**: Crea la base de datos `control_inventario`, la tabla `producto` y sus restricciones de integridad.
2. **`02_datos_prueba.sql`**: Registra los datos de prueba iniciales y comprueba las restricciones de código duplicado y stock negativo.
3. **`03_operaciones.sql`**: Ejecuta las consultas avanzadas (filtros, ordenamientos), simulación de ventas, cambio de estado por agotamiento y eliminación de registros temporales.