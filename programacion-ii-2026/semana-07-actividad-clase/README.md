# Semana 06 - Programación Orientada a Objetos

Proyecto desarrollado en clase para introducir los fundamentos de la Programación Orientada a Objetos en Java mediante un caso sencillo de una clínica veterinaria.

## Objetivo

Representar diferentes tipos de mascotas mediante clases y objetos, aplicando atributos, métodos, encapsulamiento, constructores, herencia y sobrescritura de métodos.

## Temas trabajados

- Clases y objetos.
- Atributos y métodos.
- Encapsulamiento.
- Constructores y uso de `this`.
- Herencia mediante `extends`.
- Uso de `super`.
- Sobrescritura de métodos mediante `@Override`.
- Diferencia entre sobrecarga y sobrescritura.
- Polimorfismo básico.

## Caso desarrollado

El proyecto representa diferentes mascotas dentro de un sistema veterinario.

La clase `Mascota` contiene los atributos y métodos comunes. Las clases `Perro`, `Gato` y `Ave` heredan sus características y sobrescriben el método `emitirSonido()` para proporcionar un comportamiento diferente.

```text
Mascota
├── Perro
├── Gato
└── Ave
```

## Estructura del proyecto

```text
src/
├── Mascota.java
├── Perro.java
├── Gato.java
├── Ave.java
└── Main.java
```

## Ejecución

Ejecutar la clase `Main`, que contiene el método principal del programa:

```java
public static void main(String[] args)
```

La ejecución permite comprobar:

- La creación de diferentes objetos.
- El acceso a los métodos heredados.
- La actualización controlada de los atributos.
- La validación del peso de una mascota.
- La ejecución de diferentes comportamientos mediante sobrescritura.

## Resultado esperado

Cada objeto conserva sus propios valores y responde de acuerdo con su clase real. Por ejemplo, un objeto `Perro` emite un ladrido, mientras que un objeto `Gato` emite un maullido, aunque ambos puedan almacenarse en variables de tipo `Mascota`.

## Herramientas utilizadas

- Java.
- IntelliJ IDEA.
- Git.
- GitHub.

---

**Curso:** Programación II  
**Universidad:** Universidad Mariano Gálvez de Guatemala
