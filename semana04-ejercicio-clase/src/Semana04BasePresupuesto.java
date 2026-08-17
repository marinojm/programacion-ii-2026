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
        // Suma de los tres rubros de gastos
        return alimentacion + transporte + otros;
    }

    public static double calcularSaldo(double presupuesto, double totalGastos) {
        // Resta entre el presupuesto inicial y los gastos ejecutados
        return presupuesto - totalGastos;
    }

    public static String obtenerEstado(double saldo) {
        // Determina la condición financiera según el saldo restante
        if (saldo > 0) {
            return "Superávit (Ahorro disponible)";
        } else if (saldo == 0) {
            return "Presupuesto exacto (Sin saldo restante)";
        } else {
            return "Déficit (Gastos superan el presupuesto)";
        }
    }

    public static void mostrarResumen(String nombre, double presupuesto,
                                      double totalGastos, double saldo,
                                      String estado) {
        System.out.println("\n-------------------------------------------");
        System.out.println("          RESUMEN FINANCIERO SEMANAL       ");
        System.out.println("-------------------------------------------");
        System.out.println("Usuario: " + nombre);
        System.out.printf("Presupuesto inicial : Q%.2f\n", presupuesto);
        System.out.printf("Total de gastos     : Q%.2f\n", totalGastos);
        System.out.printf("Saldo restante      : Q%.2f\n", saldo);
        System.out.println("Estado financiero   : " + estado);
        System.out.println("-------------------------------------------");
    }
}