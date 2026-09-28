import java.util.Scanner;

public class CajeroAutomatico {

    // Constantes globales de la cuenta
    static final String TITULAR = "Tu Nombre Completo Aquí"; // Reemplaza con tu nombre
    static final String NUM_CUENTA = "0000";                // Reemplaza con los últimos 4 dígitos de tu carné
    static final int PIN_CORRECTO = 2026;
    static final double COMISION_OTRA_RED = 10.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. CONTROL DE ACCESO (Máximo 3 intentos con ciclo for)
        if (!validarAcceso(scanner)) {
            System.out.println("\nCuenta bloqueada por superar el límite de intentos. Programa finalizado.");
            scanner.close();
            return; // Finaliza el programa sin mostrar el menú
        }

        // Variables de estado de la cuenta y acumuladores de la sesión
        double saldo = 1000.00;
        double saldoInicial = saldo;

        int depositosExitosos = 0;
        double totalDepositado = 0.0;

        int retirosExitosos = 0;
        double totalRetirado = 0.0;
        double totalComisiones = 0.0;

        int operacionesRechazadas = 0;
        int opcionesInvalidas = 0;

        int opcion = 0;

        // 2. MENÚ PRINCIPAL (Ciclo do-while)
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            // Validar que se ingrese un entero
            if (!scanner.hasNextInt()) {
                System.out.println("\nError: Debe ingresar un número entero de opción.");
                scanner.next(); // Limpiar entrada incorrecta
                opcionesInvalidas++;
                continue; // Regresar al inicio del menú con continue
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo(saldo);
                    break;

                case 2:
                    System.out.println("\n--- DEPÓSITO DE DINERO ---");
                    double montoDep = 0.0;

                    // Ciclo while para pedir el monto hasta que sea válido (Q0.01 a Q5,000.00)
                    while (true) {
                        System.out.print("Ingrese el monto a depositar (Máx. Q5,000.00): Q");
                        montoDep = scanner.nextDouble();

                        if (montoDep <= 0) {
                            System.out.println("El monto debe ser mayor a Q0.00. Intente nuevamente.");
                        } else if (montoDep > 5000.00) {
                            System.out.println("El monto supera el límite de Q5,000.00 por operación. Intente nuevamente.");
                        } else {
                            break; // Monto válido
                        }
                    }

                    // Procesar depósito
                    double saldoAntDep = saldo;
                    saldo += montoDep;
                    depositosExitosos++;
                    totalDepositado += montoDep;

                    System.out.printf("Depósito exitoso de Q%.2f\n", montoDep);
                    System.out.printf("Saldo anterior: Q%.2f | Saldo actualizado: Q%.2f\n", saldoAntDep, saldo);
                    break;

                case 3:
                    System.out.println("\n--- RETIRO NORMAL ---");
                    System.out.print("Ingrese el monto a retirar (Múltiplo de Q20): Q");
                    double montoRetNormal = scanner.nextDouble();

                    // Invocación a método sobrecargado retirar(saldo, monto)
                    double nuevoSaldoNorm = retirar(saldo, montoRetNormal);

                    if (nuevoSaldoNorm < saldo) {
                        // Fue aprobado
                        totalRetirado += montoRetNormal;
                        retirosExitosos++;
                        saldo = nuevoSaldoNorm;
                    } else {
                        // Fue rechazado
                        operacionesRechazadas++;
                    }
                    break;

                case 4:
                    System.out.println("\n--- RETIRO CON COMISIÓN (OTRA RED) ---");
                    System.out.println("Nota: Se aplicará una comisión fija de Q10.00.");
                    System.out.print("Ingrese el monto a retirar (Múltiplo de Q20): Q");
                    double montoRetCom = scanner.nextDouble();

                    // Invocación a método sobrecargado retirar(saldo, monto, comision)
                    double nuevoSaldoCom = retirar(saldo, montoRetCom, COMISION_OTRA_RED);

                    if (nuevoSaldoCom < saldo) {
                        // Fue aprobado
                        totalRetirado += montoRetCom;
                        totalComisiones += COMISION_OTRA_RED;
                        retirosExitosos++;
                        saldo = nuevoSaldoCom;
                    } else {
                        // Fue rechazado
                        operacionesRechazadas++;
                    }
                    break;

                case 5:
                    // Mostrar resumen actual de la sesión
                    mostrarResumen(saldoInicial, depositosExitosos, totalDepositado, retirosExitosos,
                            totalRetirado, totalComisiones, operacionesRechazadas, opcionesInvalidas, saldo);
                    break;

                case 6:
                    // Salida segura del programa
                    System.out.println("\nFinalizando sesión...");
                    mostrarResumen(saldoInicial, depositosExitosos, totalDepositado, retirosExitosos,
                            totalRetirado, totalComisiones, operacionesRechazadas, opcionesInvalidas, saldo);
                    System.out.println("\nGracias por utilizar nuestros servicios. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("\nOpción no válida. Por favor, intente nuevamente.");
                    opcionesInvalidas++;
                    continue; // Regresa inmediatamente al menú
            }

            System.out.println();

        } while (opcion != 6);

        scanner.close();
    }

    // =========================================================================
    //                            MÉTODOS AUXILIARES
    // =========================================================================

    // 1. Validar acceso con ciclo for (Máx 3 intentos)
    public static boolean validarAcceso(Scanner scanner) {
        System.out.println("==========================================");
        System.out.println("        SISTEMA DE CAJERO AUTOMÁTICO       ");
        System.out.println("==========================================");

        for (int intento = 1; intento <= 3; intento++) {
            System.out.print("Ingrese su PIN de seguridad: ");
            int pinIngresado = scanner.nextInt();

            if (pinIngresado == PIN_CORRECTO) {
                System.out.println("\n¡PIN correcto! Bienvenido(a) al sistema.");
                return true; // Acceso concedido (sale del ciclo con break implícito)
            } else {
                int restantes = 3 - intento;
                System.out.println("PIN incorrecto.");
                if (restantes > 0) {
                    System.out.println("Intentos restantes: " + restantes + "\n");
                }
            }
        }
        return false; // Tres intentos fallidos
    }

    // 2. Mostrar Menú
    public static void mostrarMenu() {
        System.out.println("\n--------- MENÚ PRINCIPAL ---------");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Realizar retiro normal");
        System.out.println("4. Realizar retiro con comisión");
        System.out.println("5. Mostrar resumen de la sesión");
        System.out.println("6. Salir");
    }

    // 3. Consulta de saldo
    public static void consultarSaldo(double saldo) {
        System.out.println("\n--- CONSULTA DE SALDO ---");
        System.out.println("Titular: " + TITULAR);
        System.out.println("Número de cuenta: ****" + NUM_CUENTA);
        System.out.printf("Saldo disponible: Q%.2f\n", saldo);
    }

    // =========================================================================
    //       SOBRECARGA DE MÉTODOS OBLIGATORIA (Mismo nombre 'retirar')
    // =========================================================================

    // VERSIÓN 1: Retiro Normal
    public static double retirar(double saldoActual, double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto a retirar debe ser mayor que Q0.00.");
            return saldoActual;
        }
        if (monto % 20 != 0) {
            System.out.println("Error: El monto a retirar debe ser múltiplo de Q20.00.");
            return saldoActual;
        }
        if (monto > 2000.00) {
            System.out.println("Error: El monto supera el límite de Q2,000.00 por operación.");
            return saldoActual;
        }
        if (monto > saldoActual) {
            System.out.println("Error: Saldo insuficiente para realizar el retiro.");
            return saldoActual;
        }

        // Operación aprobada
        double saldoAnterior = saldoActual;
        saldoActual -= monto;

        System.out.println("¡Retiro aprobado!");
        System.out.printf("Monto solicitado: Q%.2f\n", monto);
        System.out.printf("Saldo anterior: Q%.2f | Total debitado: Q%.2f | Saldo actualizado: Q%.2f\n",
                saldoAnterior, monto, saldoActual);

        return saldoActual;
    }

    // VERSIÓN 2 (Sobrecargada): Retiro con Comisión (Diferente lista de parámetros)
    public static double retirar(double saldoActual, double monto, double comision) {
        if (monto <= 0) {
            System.out.println("Error: El monto a retirar debe ser mayor que Q0.00.");
            return saldoActual;
        }
        if (monto % 20 != 0) {
            System.out.println("Error: El monto a retirar debe ser múltiplo de Q20.00.");
            return saldoActual;
        }
        if (monto > 2000.00) {
            System.out.println("Error: El monto supera el límite de Q2,000.00 por operación.");
            return saldoActual;
        }

        double totalDebitar = monto + comision;
        if (totalDebitar > saldoActual) {
            System.out.println("Error: Saldo insuficiente para cubrir el monto más la comisión de Q" + comision + ".");
            return saldoActual;
        }

        // Operación aprobada
        double saldoAnterior = saldoActual;
        saldoActual -= totalDebitar;

        System.out.println("¡Retiro con comisión aprobado!");
        System.out.printf("Monto solicitado: Q%.2f\n", monto);
        System.out.printf("Comisión de otra red: Q%.2f\n", comision);
        System.out.printf("Total debitado: Q%.2f\n", totalDebitar);
        System.out.printf("Saldo anterior: Q%.2f | Saldo actualizado: Q%.2f\n", saldoAnterior, saldoActual);

        return saldoActual;
    }

    // =========================================================================
    //                        RESUMEN DE LA SESIÓN
    // =========================================================================

    public static void mostrarResumen(double saldoInicial, int depExitosos, double totDepositado,
                                      int retExitosos, double totRetirado, double totComisiones,
                                      int opRechazadas, int opInvalidas, double saldoActual) {
        System.out.println("\n========= RESUMEN DE LA SESIÓN =========");
        System.out.printf("Saldo inicial: Q%.2f\n", saldoInicial);
        System.out.println("Cantidad de depósitos exitosos: " + depExitosos);
        System.out.printf("Total depositado: Q%.2f\n", totDepositado);
        System.out.println("Cantidad de retiros exitosos: " + retExitosos);
        System.out.printf("Total entregado en retiros: Q%.2f\n", totRetirado);
        System.out.printf("Total cobrado en comisiones: Q%.2f\n", totComisiones);
        System.out.println("Cantidad de operaciones rechazadas: " + opRechazadas);
        System.out.println("Cantidad de opciones inválidas: " + opInvalidas);
        System.out.printf("Saldo actual: Q%.2f\n", saldoActual);
        System.out.println("========================================");
    }
}