# Laboratorio: API REST de Gestión de Inventario de Productos

## Datos del Estudiante
* **Nombre completo:** Marino Jeriel Cabrera Mendoza
* **Número de carné:** 9941-23-8505

---

## Descripción del Problema
En muchos pequeños comercios y tiendas de abarrotes, el control de existencias y precios se lleva de forma manual o desordenada, lo que complica saber qué productos se están agotando o cuál representa la mayor inversión acumulada.

Para resolver esta problemática, se desarrolló un servicio backend en Java con Spring Boot que expone una API REST para administrar y consultar un inventario de productos. El sistema permite listar el catálogo, realizar búsquedas por identificador o categoría, identificar ítems con stock bajo, determinar el producto con mayor valor en inventario y generar un reporte con el resumen general de totales.

---

## Tecnologías Utilizadas
* **Lenguaje de programación:** Java 17 / 21
* **Framework principal:** Spring Boot 3.x
* **Herramienta de construcción y dependencias:** Maven
* **Entorno de desarrollo (IDE):** IntelliJ IDEA
* **Formato de datos:** JSON

---

## Requisitos para Ejecutar el Proyecto
Para compilar y correr esta aplicación localmente es necesario contar con:
1. **Java Development Kit (JDK) 17** o superior configurado en las variables de entorno.
2. **Apache Maven 3.8+** (o utilizar el ejecutable Wrapper `./mvnw` / `mvnw.cmd` incluido en el proyecto).
3. Un cliente HTTP (Postman, Insomnia, Bruno) o un navegador web para realizar las peticiones a la API.

---

## Estructura Principal del Proyecto

src/main/java/com/ejemplo/inventario/
│
├── InventarioApplication.java
├── controller/
│   └── ProductoController.java
└── model/
└── Producto.java

---

## Explicación de las Clases

* **`InventarioApplication.java`**: Es la clase principal que contiene el método `main`. Se encarga de inicializar y arrancar el contexto de la aplicación Spring Boot.
* **`Producto.java`**: Es el modelo de dominio (POJO). Representa la entidad de un producto con sus atributos: `id`, `nombre`, `categoria`, `precioUnitario` y `cantidad`.
* **`ProductoController.java`**: Es la clase controladora REST anotada con `@RestController` y `@RequestMapping("/api/productos")`. Maneja la lista de productos en memoria y expone los métodos que responden a las peticiones HTTP.

---

## Tabla de Endpoints

| Operación | Método | Ruta | Estado esperado |
| :--- | :--- | :--- | :--- |
| Listar productos | GET | /api/productos | 200 |
| Buscar por identificador | GET | /api/productos/{id} | 200 o 404 |
| Buscar por categoría | GET | /api/productos/categoria/{categoria} | 200 |
| Consultar stock bajo | GET | /api/productos/stock-bajo | 200 |
| Consultar producto de mayor valor | GET | /api/productos/mayor-valor | 200 |
| Obtener resumen | GET | /api/productos/resumen | 200 |

---

## Instrucciones para Ejecutar la Aplicación

1. Clonar o descargar el repositorio del proyecto en tu equipo local.
2. Abrir una terminal en la raíz del proyecto.
3. Ejecutar el comando para iniciar el servidor de desarrollo:
    * En Linux/macOS: `./mvnw spring-boot:run`
    * En Windows (PowerShell / CMD): `mvnw.cmd spring-boot:run`
4. La aplicación iniciará en el puerto 8080. Puedes verificar su funcionamiento haciendo peticiones a `http://localhost:8080/api/productos`.

---

## Ejemplos de Respuestas JSON

### 1. Consultar producto por ID (GET /api/productos/1)
{
"id": 1,
"nombre": "Arroz Precocido",
"categoria": "Granos",
"precioUnitario": 12.50,
"cantidad": 10
}

### 2. Consultar producto de mayor valor (GET /api/productos/mayor-valor)
{
"id": 5,
"nombre": "Detergente en Polvo",
"categoria": "Limpieza",
"precioUnitario": 45.00,
"cantidad": 3
}

### 3. Obtener resumen del inventario (GET /api/productos/resumen)
{
"cantidadProductos": 6,
"totalUnidades": 29,
"valorTotal": 444.50
}