# Examen Parcial II - Control de Tareas Personales

## Datos Estudiante
* **Nombre completo:** Marino Cabrera
* **Número de carné:** *9941-23-8505*

---

## Descripción del Problema
En el desarrollo de software actual, es común la necesidad de administrar actividades diarias de manera organizada. Este proyecto resuelve la gestión básica de tareas personales mediante una aplicación en Java estructurada con Maven, permitiendo clasificar tareas por título, descripción, nivel de prioridad (ALTA, MEDIA, BAJA) y estado de realización (pendiente o completada). Además, sienta las bases conceptuales para la futura exposición de estos datos mediante un servicio web bajo la arquitectura REST.

---

## Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 17 o superior)
* **Gestor de proyectos:** Apache Maven
* **IDE:** IntelliJ IDEA
* **Control de versiones:** Git y GitHub
* **Formato de datos:** JSON / REST API design

---

## Datos del Proyecto Maven
* **`groupId`:** `com.estudiante`
* **`artifactId`:** `Control-tareas`
* **`version`:** `1.0-SNAPSHOT`

### Explicación de los metadatos de Maven
* **`groupId`:** Identifica de manera única la organización, dominio o paquete raíz del proyecto (sigue la convención de dominio invertido).
* **`artifactId`:** Es el nombre único del proyecto o subproyecto en específico. Se utiliza también para nombrar el archivo ejecutable resultante (`.jar`).
* **`version`:** Indica la fase actual de desarrollo del proyecto. La palabra `SNAPSHOT` señala que se encuentra en una etapa activa de desarrollo y pruebas.

---

## Instrucciones para Compilar y Ejecutar el Proyecto

Puedes compilar y ejecutar este proyecto desde la interfaz de IntelliJ IDEA o utilizando el Maven Wrapper (`mvnw`) en la terminal.

### 1. Limpiar y Compilar el proyecto
```bash
.\mvnw clean compile
```

### 2. Ejecutar Pruebas
```bash
.\mvnw test
```

### 3. Empaquetar y generar el archivo `.jar`
```bash
.\mvnw package
```
*El archivo `.jar` resultante se generará automáticamente en la carpeta `target/Control-tareas-1.0-SNAPSHOT.jar`.*

### 4. Ejecución desde IntelliJ IDEA
1. Abre el archivo `Main.java` ubicado en `src/main/java/com/estudiante/Main.java`.
2. Presiona el botón verde de **Run** (▶) situado a la izquierda de la clase principal.
3. Observa la salida formateada con el conteo de tareas pendientes y completadas en el panel inferior.

---

## Diseño de la API REST

| Operación | Método HTTP | Endpoint | Respuesta esperada |
| :--- | :--- | :--- | :--- |
| Consultar todas las tareas | `GET` | `/api/tareas` | `200 OK` |
| Consultar una tarea | `GET` | `/api/tareas/{id}` | `200 OK` |
| Registrar una tarea | `POST` | `/api/tareas` | `201 Created` |
| Modificar una tarea | `PUT` | `/api/tareas/{id}` | `200 OK` |
| Eliminar una tarea | `DELETE` | `/api/tareas/{id}` | `204 No Content` |
| Consultar una tarea inexistente | `GET` | `/api/tareas/{id}` | `404 Not Found` |

---

## Ejemplo de Objeto JSON

Representación de la lista completa de tareas devuelta por la API (`GET /api/tareas`):

```json
[
  {
    "id": 1,
    "titulo": "Estudiar Maven y REST",
    "descripcion": "Repasar la estructura de paquetes, comandos del ciclo de vida y respuestas HTTP.",
    "prioridad": "ALTA",
    "completada": false
  },
  {
    "id": 2,
    "titulo": "Realizar ejercicios",
    "descripcion": "Rutina de cardio por 30 minutos.",
    "prioridad": "MEDIA",
    "completada": true
  }
]
```