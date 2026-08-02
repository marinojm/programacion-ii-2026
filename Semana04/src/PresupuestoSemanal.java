import java.util.Scanner;

public class PresupuestoSemanal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su presupuesto semanal: Q");
        double presupuesto = scanner.nextDouble();

        System.out.print("Gasto de alimentación: Q");
        double alimentacion = scanner.nextDouble();

        System.out.print("Gasto de transporte: Q");
        double transporte = scanner.nextDouble();

        System.out.print("Otros gastos: Q");
        double otros = scanner.nextDouble();

        // Llamar métodos aquí
        double totalGastos = calcularTotalGastos(alimentacion, transporte, otros);
        double saldo = calcularSaldo(presupuesto, totalGastos);
        String estado = obtenerEstado(saldo);

        mostrarResumen(nombre, presupuesto, totalGastos, saldo, estado);
        scanner.close();

    }

    public static double calcularTotalGastos(double alimentacion,
                                             double transporte,
                                             double otros) {
        // Completar

        return alimentacion + transporte + otros;
    }

    public static double calcularSaldo(double presupuesto, double totalGastos) {
        // Completar
        return presupuesto - totalGastos;
    }

    public static String obtenerEstado(double saldo) {
        // Completar
        return "";
    }

    public static void mostrarResumen(String nombre, double presupuesto,
                                      double totalGastos, double saldo,
                                      String estado) {
        // Completar
    }
}
