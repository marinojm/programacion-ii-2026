# Laboratorio: API REST de Gestión de Inventario de Productos

## Estudiante
* **Nombre:** Marino Jeriel Cabrera Mendoza
* **Carné:** 9941-23-8505

---

## Descripción del Problema
En muchos pequeños comercios y tiendas de abarrotes, el control de existencias y precios se lleva de forma manual o desordenada, lo que complica saber qué productos se están agotando o cuál representa la mayor inversión.

Para resolver esto, se desarrolló un backend en Java con Spring Boot que expone una API REST para gestionar un inventario básico de productos. El sistema permite listar inventarios, filtrar por categorías, detectar ítems con stock bajo, encontrar el producto de mayor valor acumulado y consultar un resumen general de totales.

---

## Tecnologías Utilizadas
* **Lenguaje:** Java 17 / 21
* **Framework:** Spring Boot 3
* **Entorno de Desarrollo (IDE):** IntelliJ IDEA
* **Gestor de Dependencias:** Maven
* **Formato de Intercambio de Datos:** JSON

---

## Requisitos para Ejecutar el Proyecto
Para compilar y ejecutar esta aplicación localmente necesitas:
1. **JDK 17** o superior instalado y configurado en las variables de entorno.
2. **Apache Maven 3.8+** (o utilizar el ejecutable `./mvnw` incluido en el proyecto).
3. Un navegador web o un cliente HTTP (como Postman, Bruno o Insomnia) para probar los endpoints.

---

## Estructure Principal del Proyecto
```text
src/main/java/com/ejemplo/inventario/
│
├── InventarioApplication.java
├── controller/
│   └── ProductoController.java
└── model/
    └── Producto.java