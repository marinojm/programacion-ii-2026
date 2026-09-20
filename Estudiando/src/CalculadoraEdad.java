import java.util.Scanner;

public class CalculadoraEdad {
    public static void main(String[] args){

        //inicialización del objeto Scanner para la captura de datos desde la consola
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre completo ");
        String nombreCompleto = scanner.nextLine();

        System.out.print("Ingrese su carnet: ");
        String carnetEstudiante = scanner.nextLine();

        System.out.print("Ingrese el año de su nacimiento: ");
        String anioNacimiento = scanner.nextLine();

        System.out.print("Ingrese el año actual: ");
        int anioActual = scanner.nextInt();

        //procesamiento, calculos y valiadcion de la mayoria de edad en cumplimiento
        int edadAproximada = anioActual - anioNacimiento;
        int edadMeses = edadAproximada * 12;
        boolean esMayorDeEdad = edadAproximada >= 18;

        
    }


}
