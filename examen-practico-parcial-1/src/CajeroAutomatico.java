import java.util.Scanner;

public class CajeroAutomatico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 1500.00; //saldo predeterminado para usuario
        int opcion = 0;

        System.out.println("CAJERO AUTOMATICO");

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: " );
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo(saldo);
                break;

                case 2:
                    System.out.print("\n Ingrese el monto a depositar: Q");
                    double montoDeposito = scanner.nextDouble();
                    saldo = depositar(saldo, montoDeposito);
                    break;

                case 3:
                    System.out.println( "TIPO DE RETIRO" );
                    System.out.println("1. Monto personalizado");
                    System.out.println("2. Retiro rapido (Q100, Q200, Q500)");
                    System.out.println("Elija una opcion: ");
                    int tipoRetiro = scanner.nextInt();

                    if (tipoRetiro == 1) {
                        System.out.print("Ingrese el monto a retirar: Q");
                        double montoRetiro = scanner.nextDouble();
                        saldo = retirar(saldo, montoRetiro);
                    } else if (tipoRetiro == 2) {
                        System.out.println("Seleccione el monto rápido: 1) Q100 2) Q200 3)Q500");
                        int opcionRapida = scanner.nextInt();
                        saldo = retirar(saldo, opcionRapida);
                    } else {
                        System.out.println("Opcion de retiro invalida");
                    }
                    break;
                case 4:
                    System.out.print("\nIngrese el código de 8 digitos de la remesa");
                    String codigoRemesa = scanner.next();
                    System.out.print("Ingrese el monto de la remesa a cobrar: Q");
                    double montoRemesa = scanner.nextDouble();
                    saldo = cobrarRemesa(saldo, codigoRemesa, montoRemesa);
                    break;

                case 5:
                    System.out.println("\nPAGO DE SERVICIOS");
                    System.out.println("1. Pago con monto exacto de factura");
                    System.out.println("2. Pago de tarifa fija por plan");
                    System.out.print("Elija la modalidad de pago: ");
                    int modoPago = scanner.nextInt();

                    System.out.print("Ingrese el servicio (luz, agua o internet): ");
                    String servicio = scanner.next();

                    if (modoPago == 1) {
                        System.out.print("Ingrese el monto según su factura: Q");
                        double montoFactura = scanner.nextDouble();
                        saldo = pagoServicio(saldo, servicio, montoFactura);
                    } else if (modoPago == 2){
                        System.out.println("Seleccione el plan: 1) Plan básico(Q150) 2) Plan Complpeto (Q300)");
                        int tipoPlan = scanner.nextInt();
                        saldo = pagoServicio(saldo, servicio, tipoPlan);
                    } else {
                        System.out.println("Modalidad ed pago no valida");
                    }
                    break;

                case 6:
                    System.out.println("\nGracias por utilizar nuestros servicios. Hasta pronto");
                    break;

                default:
                    System.out.println("\nOpcion no valida. Por favor intente nuevamente.");
                    break;
            }

            System.out.println();

        } while (opcion != 6);

        scanner.close();
    }

    public static void mostrarMenu() {

        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Consultar Saldo");
        System.out.println("2. Depositar Dinero");
        System.out.println("3. Retirar Dinero");
        System.out.println("4. Cobrar Remesa");
        System.out.println("5. Pagar Servicio");
        System.out.println("6. Salir");
    }

    //mostrar saldo en pantalla
    public static void consultarSaldo(double saldo) {
        System.out.printf("\nSu saldo actual es: Q%.2f\n, saldo", saldo);
    }

    //procesa depositos en la cuenta
    public static double depositar(double saldo, double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.printf("Deposito exitoso. Nuevo saldo: Q%.2f\n", saldo);
        } else {
            System.out.printf("Error: el monto a depositar debe ser mayor a cero.");
        }
        return saldo;
    }

    //method overloading

    //version 1: retiro por monto personalizado
    public static double retirar(double saldo, double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a retirar debe ser mayor a cero");
        } else if (monto > saldo) {
                System.out.println("Error: saldo insuficiente para realizar el retiro.");
            } else {
                    saldo -= monto;
                    System.out.printf("Retiro exitoso de Q%.2f\n", monto, saldo);
                }
                return saldo;
            }

            //version 2: overloading reitro rápido por opción predefinida
            public static double retirar(double saldo, int opcionRapida) {
                double monto = 0;
                switch (opcionRapida) {
                    case 1: monto = 100; break;
                    case 2: monto = 200; break;
                    case 3: monto = 500; break;
                    default:
                        System.out.println("Opcion rápida no válida.");
                        return saldo;
                }
                //reutiliza la logica de validacion de saldo llamando a la version 1
                return retirar(saldo, monto);
            }

            //metodo remesas
            public static double cobrarRemesa(double saldo, String codigo, double monto){
                if (codigo.length() < 5) {
                    System.out.println("Error: codigo de remesa no valido.");
                } else if (monto <= 0) {
                    System.out.println("Error: monto de remesa invalido.");
                } else {
                    saldo += monto;
                    System.out.printf("Remesa %s cobrada con exito por Q%.2f. Nuevo saldo: Q%.2f\n", codigo, monto, saldo);
                }
                return saldo;
            }

            //overloading: pago de servicios
            //version 1: pago de servicio con monto exacto
            public static double pagoServicio(double saldo, String servicio, double monto) {
                if (monto <= 0) {
                    System.out.println("Error: el monto a pagar debe ser positivo.");
                } else if (monto > saldo) {
                    System.out.println("Error: saldo insuficiente para pagar el serviciode " + servicio + ".");
                } else {
                    saldo -= monto;
                    System.out.printf("Pago de %s realizado por Q%.2f. Nuevo saldo: Q%.2f\n", servicio, monto, saldo);
                }
                return saldo;
            }

            //version 2 overloadiing: pago de servicio mediante tarifa fija
            public static double pagarServicio(double saldo, String servicio, int tipoPlan) {
                double tarifa = 0;
                if (tipoPlan == 1) {
                    tarifa = 150.00;
                } else if (tipoPlan == 2) {
                    tarifa = 300.00;
                } else {
                    System.out.println("Tipo de plan no valido.");
                    return saldo;
                }

                return pagoServicio(saldo, servicio, tarifa);
    }
}
