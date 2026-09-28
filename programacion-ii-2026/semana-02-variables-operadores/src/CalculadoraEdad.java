import java.util.Scanner;

public class CalculadoraEdad {
    public static void main(String[] args){

        //inicializacion del objeto Scanner para la captura de datos desde la consola
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre completo ");
        String nombreCompleto = scanner.nextLine();

        System.out.print("Ingrese su carnet: ");
        String carnetEstudiante = scanner.nextLine();

        System.out.print("Ingrese el año de su nacimiento: ");
        int anioNacimiento = scanner.nextInt();

        System.out.print("ingrese el año actual: ");
        int anioActual = scanner.nextInt();

        //procesamiento, calculos y validacion de la mayoria de edad en cumplimiento con el requisiton
        int edadAproximada = anioActual - anioNacimiento;
        int edadEnMeses = edadAproximada * 12;
        boolean esMayorDeEdad = edadAproximada >= 18;

        //impresion del informe de resultados en consola
        System.out.println("Resultado: ");
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Carnet: " + carnetEstudiante);
        System.out.println("Edad aproximada: " + edadAproximada + " años");
        System.out.println("Edad aproximada en meses: " + edadEnMeses + " meses");
        System.out.println("Es mayor de edad?: " + (esMayorDeEdad ? "Sí" : "No"));

        scanner.close();
    }
}