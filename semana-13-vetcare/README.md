# API REST de Gestión de Biblioteca

## 1. Nombre de la actividad
Pruebas y Documentación de API RESTful con Spring Boot y PostgreSQL (Semana 13 - Biblioteca API).

## 2. Descripción del problema
En el desarrollo de software moderno, es fundamental gestionar de manera eficiente catálogos de información digital. El problema consistió en diseñar, implementar y probar una API REST para la administración de un registro de libros personales, permitiendo realizar operaciones CRUD completas (Crear, Leer, Actualizar y Eliminar), aplicar filtros por categorías y disponibilidad, y asegurar la integridad de los datos mediante restricciones de base de datos como claves primarias y campos únicos.

## 3. Tecnologías utilizadas
* **Lenguaje:** Java 17+
* **Framework:** Spring Boot (Spring Web, Spring Data JPA)
* **Base de datos:** PostgreSQL 18
* **Gestor de base de datos:** pgAdmin 4
* **Herramienta de pruebas:** Postman
* **Gestor de dependencias:** Maven

## 4. Estructura del proyecto
La arquitectura del proyecto sigue el patrón de capas estándar de Spring Boot:
```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── tuproyecto/
│   │           └── biblioteca/
│   │               ├── BibliotecaApplication.java
│   │               ├── controller/
│   │               │   └── LibroController.java
│   │               ├── model/
│   │               │   └── Libro.java
│   │               └── repository/
│   │                   └── LibroRepository.java
│   └── resources/
│       └── application.properties
```

## 5. Configuración general de la base de datos
La base de datos relacional se gestiona en PostgreSQL.

### Script de creación de la tabla:
```sql
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
```

### Configuración en `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_personal
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 6. Tabla de endpoints

| Método | Endpoint | Descripción | Código HTTP Esperado |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/libros` | Registra un nuevo libro. | `201 Created` / `500` (Si duplica código) |
| **GET** | `/api/libros` | Consulta general de todos los libros. | `200 OK` |
| **GET** | `/api/libros/{id}` | Busca un libro por su ID específico. | `200 OK` o `404 Not Found` |
| **GET** | `/api/libros/categoria/{categoria}` | Filtra y busca libros por categoría. | `200 OK` |
| **GET** | `/api/libros/disponibles` | Consulta únicamente los libros con estado disponible. | `200 OK` |
| **PUT** | `/api/libros/{id}` | Actualiza por completo los datos de un libro existente. | `200 OK` |
| **DELETE** | `/api/libros/{id}` | Elimina un libro de la base de datos por su ID. | `204 No Content` |

## 7. Instrucciones para ejecutar la aplicación
1. Clonar o descargar el repositorio del proyecto en tu entorno local.
2. Asegurar tener levantado el servidor de **PostgreSQL** y crear la base de datos `biblioteca_personal` ejecutando el script SQL indicado en la sección 5.
3. Configurar las credenciales de la base de datos en el archivo `src/main/resources/application.properties`.
4. Ejecutar la aplicación desde tu IDE favorito (IntelliJ IDEA, Eclipse) ejecutando la clase principal `BibliotecaApplication.java`, o mediante la terminal con Maven:
   ```bash
   mvn spring-boot:run
   ```
5. Abrir **Postman** y realizar las peticiones HTTP apuntando a `http://localhost:8080/api/libros`.

## 8. Nombre y carné del estudiante
* **Nombre:** Marino Cabrera
* **Carné:** 9941-23-8505