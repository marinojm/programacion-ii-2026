public class Main {
    public static void main(String[] args) {
        Figura[] figuras = new Figura[3];

        figuras[0] = new Circulo("círculo", 5.0);
        figuras[1] = new Rectangulo("rrctángulo", 4.0, 6.0);
        figuras[2] = new Triangulo("Trirnagulo", 3.0, 8.0);

        for (Figura figura : figuras) {
            figura.mostrarInformacion();
            System.out.printf("Área: %.2f%n", figura.calcularArea());
            System.out.println("---------------------------");
        }
    }
}