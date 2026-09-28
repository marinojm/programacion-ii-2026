# Sistema de Cálculo de Envíos - POO

## Nombre del proyecto
Sistema de Cálculo de Envíos - POO

## Descripción breve
Aplicación de consola en Java orientada a objetos para la gestión y cálculo de costos de envíos nacionales e internacionales, aplicando tarifas base, cobros por distancia y cargos de gestión internacional con validación continua de entradas.

## Objetivo
Desarrollar una solución modular en Java aplicando los pilares de la Programación Orientada a Objetos (clases, objetos, atributos, métodos, encapsulamiento, herencia, sobrecarga, sobrescritura y polimorfismo) para determinar con precisión el costo final de distintas modalidades de envío.

## Temas aplicados
* **Encapsulamiento:** Atributos privados con métodos getters/setters y constructores de inicialización.
* **Herencia:** Extensión de la clase base Envio hacia las subclases EnvioNacional y EnvioInternacional.
* **Sobrescritura (@Override):** Adaptación del método calcularCostoFinal() según las reglas específicas de cada tipo de envío.
* **Sobrecarga:** Firmas alternativas del método mostrarResumen() para alternar entre vista simplificada y desglose completo.
* **Polimorfismo:** Manejo de instancias de subclases mediante referencias del tipo padre Envio.
* **Validación de datos:** Control con Scanner en bucles para reintentar entradas inválidas sin interrumpir la ejecución.

## Estructura del proyecto
src/
├── Envio.java
├── EnvioNacional.java
├── EnvioInternacional.java
└── Main.java
README.md

## Instrucciones para ejecutar el programa
1. Abrir la terminal o línea de comandos del sistema.
2. Navegar hasta la carpeta raíz del proyecto donde se ubica la carpeta src.
3. Compilar los archivos fuente .java ejecutando: `javac src/*.java`
4. Ejecutar la clase principal Main ejecutando: `java -cp src Main`

## Resultado esperado
El sistema despliega un menú interactivo en consola. Al seleccionar una opción válida (envío nacional o internacional) e ingresar los datos requeridos, el programa procesa la información e imprime un comprobante con el peso, costo base, cargos adicionales y costo final. Si se ingresa un dato inválido (como peso negativo o texto no numérico), el sistema muestra un mensaje de error y vuelve a solicitar el valor sin detener la aplicación.