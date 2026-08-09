import java.util.Scanner;

public class CajeroAutomatico {

    static final String TITULAR = "Marino Jeriel Cabrera Mendoza";
    static final String NUM_CUENTA = "8505";
    static final int PIN_CORRECTO = 2026;
    static final double COMISION_OTRA_RED = 10.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1. acceso
        if (!validarAcceso(scanner)) {
            System.out.println("\nCuenta bloqueada por superar el limite de intentos. Programa finalizado.");
            scanner.close();
            return;
        }

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

        //2. menu principal do while
        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");

            if (!scanner.hasNextInt()) {
                System.out.println("\nError: Debe ingresar un numero entero de opcion.");
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
                    System.out.println("\nDEPOSITO DE DINERO");
                    double montoDep = 0.0;

                    while (true) {
                        System.out.print("Ingrese el monto a depositar. Maximo Q5000.00: Q");
                        montoDep = scanner.nextDouble();

                        if (montoDep <= 0) {
                            System.out.println("El monto debe ser mayor a Q0.00. Intente de nuevo.");
                        } else if (montoDep > 5000.00) {
                            System.out.println("El monto supera el limite de Q5000.00. Intente de nuevo.");
                        } else {
                            break;
                        }
                    }

                    //proceso de deposito
                    double saldoAntDep = saldo;
                    saldo += montoDep;
                    depositosExitosos++;
                    totalDepositado += montoDep;

                    System.out.printf("deposito exitoso de Q%.2f\n", montoDep);
                    System.out.printf("Saldo anterior: Q%.2f | Saldo actualizado: Q%.2f\n", saldoAntDep, saldo);
                    break;

                case 3:
                    System.out.println("\nRETIRO NORMAL");
                    System.out.print("Ingrese el monto a retirar: Q");
                    double montoRetNormal = scanner.nextDouble();

                    double nuevoSaldoNorm = retirar(saldo, montoRetNormal);

                    if (nuevoSaldoNorm < saldo) {
                        totalRetirado += montoRetNormal;
                        retirosExitosos++;
                        saldo = nuevoSaldoNorm;
                    } else {
                    } operacionesRechazadas++;
                    break;

                case 4:
                    System.out.println("\nRETIRO CON COMISION");
                    System.out.println("Nota: Se aplicar una comision de Q10.00");
                    System.out.print("Ingresar monto a retirar: Q");
                    double montoRetCom = scanner.nextDouble();

                    double nuevoSaldoCom = retirar(saldo, montoRetCom, COMISION_OTRA_RED);

                    if (nuevoSaldoCom < saldo) {

                        totalRetirado += montoRetCom;
                        totalComisiones += COMISION_OTRA_RED;
                        retirosExitosos++;
                        saldo = nuevoSaldoCom;
                    } else {

                        operacionesRechazadas++;
                    }
                    break;

                case 5:
                    mostrarResumen(saldoInicial, depositosExitosos, totalDepositado, retirosExitosos,
                            totalRetirado, totalComisiones, operacionesRechazadas, opcionesInvalidas, saldo);
                    break;

                case 6:
                    System.out.println("\nFinalizando sesion");
                    mostrarResumen(saldoInicial, depositosExitosos, totalDepositado, retirosExitosos,
                            totalRetirado, totalComisiones, operacionesRechazadas, opcionesInvalidas, saldo);
                    System.out.println("\nGracias por utilizar nuestro servicio. Hasta pronto");
                    break;

                default:
                    System.out.println("\nOpción no valida. Por favor intente nuevamente.");
                    opcionesInvalidas++;
                    continue;
                    }
            System.out.println();

                    } while (opcion != 6);
        scanner.close();
            }

            //validar acceso con ciclo for
            public static boolean validarAcceso(Scanner scanner) {
        System.out.println("CAJERO AUTOMATICO");

                for (int intento = 1; intento <= 3; intento++) {
                    System.out.print("Ingrese PIN de seguridad: ");
                    int pinIngresado = scanner.nextInt();

                    if (pinIngresado == PIN_CORRECTO) {
                        System.out.println("\nPIN correcto. Bienvenido.");
                        return true;
                    } else {
                        int restantes = 3 - intento;
                        System.out.println("PIN incorrecto.");
                        if (restantes > 0) {
                            System.out.println("Intentos restantes: " + restantes + "\n");
                        }
                    }
                }
                return false;
    }

    //mostrar menu
    public static void mostrarMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Realizar retiro normal");
        System.out.println("4. Realizar retiro con comision");
        System.out.println("5. Mostrar resumen de la sesion");
        System.out.println("6. Salir");
    }

        //conulta de saldo
        public static void consultarSaldo(double saldo){
            System.out.println("\n--- CONSULTA DE SALDO ---");
            System.out.println("Titular: " + TITULAR);
            System.out.println("Número de cuenta: ****" + NUM_CUENTA);
            System.out.printf("Saldo disponible: Q%.2f\n", saldo);
        }

        //overloading
        //version 1
        public static double retirar(double saldoActual, double monto) {
            if (monto <= 0) {
                System.out.println("Error: el monto a retirar debe ser mayor que Q0.00.");
                return saldoActual;
            }
            if (monto % 20 != 0) {
                System.out.println("Error: el monto a retirar debe ser multiplo de Q20.00.");
                return saldoActual;
            }
            if (monto > 2000.00) {
                System.out.println("Error: el monto supera el limite de Q2000.00 por operacion.");
                return saldoActual;
            }
            if (monto > saldoActual) {
                System.out.println("Error: su saldo insuficiente para realizar el retiro.");
                return saldoActual;
            }

            double saldoAnterior = saldoActual;
            saldoActual -= monto;

            System.out.println("Retiro aprobado");
            System.out.printf("Monto solicitado: Q%.2f\n", monto);
            System.out.printf("Saldo anterior: Q%.2f | Total debitado: Q%.2f | Saldo actualizado: Q%.2f\n",
                    saldoAnterior, monto, saldoActual);

            return saldoActual;
        }

        //version 2
        public static double retirar(double saldoActual, double monto, double comision) {
            if (monto <= 0) {
                System.out.println("Error: el monto a retirar debe ser mayor que Q0.00.");
                return saldoActual;
            }
            if (monto % 20 != 0) {
                System.out.println("Error: el monto a retirar debe ser multiplo de Q20.00.");
                return saldoActual;
            }
            if (monto > 2000.00) {
                System.out.println("Error: El monto supera el limite de Q2000.00 por operacion.");
                return saldoActual;
            }

            double totalDebitar = monto + comision;
            if (totalDebitar > saldoActual) {
                System.out.println("Error: su saldo es insuficiente para cubrir el monto mas la comisian de Q" + comision + ".");
                return saldoActual;
            }

            double saldoAnterior = saldoActual;
            saldoActual -= totalDebitar;

            System.out.println("Retiro con comision aprobado");
            System.out.printf("Monto solicitado: Q%.2f\n", monto);
            System.out.printf("Comision de otra red: Q%.2f\n", comision);
            System.out.printf("Total debitado: Q%.2f\n", totalDebitar);
            System.out.printf("Saldo anterior: Q%.2f | Saldo actualizado: Q%.2f\n", saldoAnterior, saldoActual);

            return saldoActual;
        }

        //resumen
        public static void mostrarResumen(double saldoInicial, int depExitosos, double totDepositado,
        int retExitosos, double totRetirado, double totComisiones,
        int opRechazadas, int opInvalidas, double saldoActual) {
            System.out.println("\nRESUMEN DE LA SESION");
            System.out.printf("Saldo inicial: Q%.2f\n", saldoInicial);
            System.out.println("Cantidad de depositos exitosos: " + depExitosos);
            System.out.printf("Total depositado: Q%.2f\n", totDepositado);
            System.out.println("Cantidad de retiro exitosos: " + retExitosos);
            System.out.printf("Total entregado en retiros: Q%.2f\n", totRetirado);
            System.out.printf("Total cobrado en comisiones: Q%.2f\n", totComisiones);
            System.out.println("Cantidad de operaciones rechazadas: " + opRechazadas);
            System.out.println("Cantidad de opciones invalidas: " + opInvalidas);
            System.out.printf("Saldo actual: Q%.2f\n", saldoActual);
            System.out.println("========================================");

        }

    }