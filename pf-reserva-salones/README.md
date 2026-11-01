# Sistema de Reserva de Salones (PF-15)

* **Nombre del Proyecto:** Sistema de Reserva de Salones (PF-15)
* **Nombre Completo del Estudiante:** Marino Jeriel Cabrera Mendoza
* **Número de Carné:** 9941-23-8505

## Descripción del problema
La gestión manual de espacios y salones de eventos en instituciones o empresas suele generar conflictos operativos, tales como sobreocupación de capacidad, duplicidad de reservas en un mismo horario, falta de trazabilidad en los estados de los salones y errores humanos en el cálculo de costos. El sistema automatiza este flujo mediante una arquitectura web centralizada, garantizando el control de disponibilidad en tiempo real y la validación estricta de las reglas del negocio.

## Tecnologías utilizadas
* **Backend:** Java 17, Spring Boot, Spring Data JPA, Hibernate, Bean Validation (`jakarta.validation`).
* **Frontend:** HTML5, CSS3, JavaScript (Vanilla JS), diseño responsivo con componentes modales.
* **Base de Datos:** PostgreSQL (`reserva_salones_db`).
* **Gestor de Dependencias:** Maven.
* **Herramientas de Apoyo:** IntelliJ IDEA, pgAdmin 4, Draw.io.

## Estructura general del sistema
El proyecto sigue una arquitectura en capas basada en Spring Boot y un cliente web desacoplado:
* `controller`: Controladores REST que exponen los endpoints para clientes HTTP.
* `service`: Lógica de negocio, validaciones de capacidad y prevención de traslapes.
* `repository`: Interfaces de Spring Data JPA para la persistencia y consultas a PostgreSQL.
* `entity`: Clases de dominio mapeadas como tablas relacionales (`Salon` y `Reserva`).
* `dto`: Objetos de transferencia de datos con validaciones estrictas.
* `exception`: Manejo centralizado de errores mediante `@RestControllerAdvice`.
* `static`: Archivos estáticos del frontend (`index.html`, `style.css`, `app.js`).

## Requisitos para ejecutar el proyecto
* **Java Development Kit (JDK):** Versión 17 o superior.
* **PostgreSQL y pgAdmin:** Servidor de base de datos relacional activo.
* **Navegador Web:** Google Chrome, Microsoft Edge o Firefox.

## Configuración de PostgreSQL
1. Crear una base de datos en PostgreSQL con el nombre `reserva_salones_db`.
2. Configurar las credenciales de conexión en el archivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/reserva_salones_db
   spring.datasource.username=tu_usuario_postgres
   spring.datasource.password=tu_contraseña_postgres
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

## Instrucciones para iniciar el backend
1. Abrir el proyecto en IntelliJ IDEA.
2. Sincronizar las dependencias de Maven desde el archivo `pom.xml`.
3. Navegar a la clase principal de la aplicación: `src/main/java/com/umg/reservas/reservasalones/PfReservasSalonesApplication.java`.
4. Hacer clic en el botón verde de Play ($\triangleright$) para ejecutar el proyecto utilizando la configuración de Maven.

## Instrucciones para abrir el frontend
1. Asegurarse de que el backend se encuentre ejecutándose correctamente en el puerto local 8080.
2. Abrir el navegador web de preferencia.
3. Ingresar a la URL raíz del sistema: `http://localhost:8080/`.
4. Utilizar la interfaz gráfica interactiva para la gestión de salones y el control de reservas.

## Descripción de los endpoints

### Módulo de Salones:
* `GET /api/salones` - Retorna el listado completo de salones registrados.
* `POST /api/salones` - Registra un nuevo salón en el sistema.
* `PUT /api/salones/{id}` - Actualiza la información o el estado activo/inactivo de un salón específico.

### Módulo de Reservas:
* `GET /api/reservas` - Lista todas las reservaciones almacenadas en la base de datos.
* `POST /api/reservas` - Crea una nueva reserva validando disponibilidad física, horarios y capacidad.
* `DELETE /api/reservas/{id}` - Cancela o elimina una reserva existente.

## Reglas principales del negocio
* **Validación de Capacidad:** El sistema rechaza de forma automática (código HTTP 400) cualquier reserva cuya cantidad de invitados supere la capacidad máxima permitida por el salón.
* **Prevención de Traslapes:** Se valida mediante lógica de servicio que no existan cruces de fechas u horas activas para un mismo salón.
* **Control de Salones Activos:** Únicamente los salones con estado `activo = true` pueden ser seleccionados al momento de registrar nuevas reservas.
* **Manejo Centralizado de Excepciones:** Respuestas HTTP normalizadas (400 para errores de validación, 404 para recursos no encontrados y 409 para conflictos de reglas de negocio).