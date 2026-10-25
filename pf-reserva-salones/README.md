# PF-15: Sistema de Reservas de Salones para Eventos

> **Nota:** Este proyecto se encuentra actualmente en desarrollo. El README definitivo y detallado se actualizará cuando el proyecto final esté completo.

---

## Descripción del Proyecto
Aplicación backend desarrollada en **Spring Boot, JPA y PostgreSQL** orientada a la gestión de salones de eventos. En esta etapa se ha implementado la capa de persistencia, servicios, control de excepciones y la validación de datos de entrada mediante DTOs para la entidad principal `Salon`.

---

## Tecnologías y Dependencias
- **Java**
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - Spring Boot Validation (`spring-boot-starter-validation`)
- **PostgreSQL** (Base de datos relacional)
- **Maven** (Gestor de dependencias)

---

## ## DTO, validaciones y manejo de errores

- **Entidad utilizada:** `Salon` (Entidad principal del sistema de reservas).
- **DTO creado:** `SalonDto` para la recepción segura de datos de registro y modificación (excluyendo el ID generado automáticamente por la base de datos).
- **Validaciones implementadas:** Uso de anotaciones de validación (`@NotBlank`, `@NotNull`, `@Min`, `@Size`) para asegurar la integridad y el formato correcto de los datos de entrada.
- **Excepción personalizada:** `RecursoNoEncontradoException` para manejar de forma controlada las solicitudes de identificadores de salones inexistentes.
- **Códigos HTTP utilizados:**
    - `201 Created` para registros exitosos.
    - `200 OK` para consultas y actualizaciones correctas.
    - `400 Bad Request` para errores de validación o parámetros inválidos.
    - `404 Not Found` para recursos no encontrados.
- **Forma de ejecutar y probar la API:**
    1. Configurar las credenciales de conexión a PostgreSQL en el archivo `application.properties`.
    2. Ejecutar el proyecto mediante Maven usando la terminal:
       ```bash
       mvn spring-boot:run
       ```
    3. Realizar las pruebas consumiendo los endpoints expuestos en `http://localhost:8080/api/salones` a través de Postman o cualquier cliente HTTP.