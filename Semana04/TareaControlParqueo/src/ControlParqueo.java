import java.util.Scanner;
public class ControlParqueo {

    static final String Nombre = "Marino Jeriel Cabrera Mendoza";
    static final String Carne = "9941-23-8505";

    //tarifas y recargos
    static final double TARIFA_MOTO = 5.00;
    static final double TARIFA_AUTO = 8.00;
    static final double TARIFA_PICKUP = 12.00;
    static final double RECARGO_TICKET_PERDIDO = 50.00;
    static final double PORCENTAJE_DESCUENTO = 0.15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mostrarEncabezado();

        //datos iniciales
        int cantidadVehiculos = solicitarEnteroPositivo(scanner, "Ingrese la cantidad de vehiculos a registrar: ");

        // contadores y acumuladores para el resumen de la jornada
        int contadorMotos = 0;
        int contadorAutos = 0;
        int contadorPickups = 0;
        int contadorTicketsPerdidos = 0;
        double totalRecaudado = 0.0;

        double pagoMasAlto = -1.0;
        String placaPagoMasAlto = "";
        String tipoPagoMasAlto = "";

        //2 procesamiento de vehiculos mediante ciclo for
        for (int i = 1; i <= cantidadVehiculos; i++) {
            System.out.println("      REGISTRO DE VEHICULO #" + i);

            System.out.print("ingrese numero de placas: ");
            String placa = scanner.next().toUpperCase();

            // Solicitud y validación del tipo de vehículo (1-3)
            int tipoVehiculo = solicitarTipoVehiculo(scanner);

            //reto extra: solicitud y validacion de tipo de vhiculo
            System.out.println("\nHORA ENTRADA");
            int horaEntrada = solicitarRango(scanner, "Hora de entrada (0-23): ", 0, 23);
            int minEntrada = solicitarRango(scanner, "Minuto de entrada (0-59): ", 0, 59);

            System.out.println("\nHORA SALIDA");
            int horaSalida = solicitarRango(scanner, "Hora de salida (0-23): ", 0, 23);
            int minSalida = solicitarRango(scanner, "Minuto de salida (0-59): ", 0, 59);

            //tiempo
            int minutosTranscurridos = calcularMinutosTranscurridos(horaEntrada, minEntrada, horaSalida, minSalida);
            int horasCobradas = calcularHorasCobradas(minutosTranscurridos);
            int horasExactas = minutosTranscurridos / 60;
            int minutosRestantes = minutosTranscurridos % 60;

            //ticket perdido
            boolean perdioTicket = solicitarRespuestaBooleana(scanner, "¿Perdió el ticket? (S/N): ");

            //tarifas
            double tarifaPorHora = obtenerTarifa(tipoVehiculo);
            String nombreTipo = obtenerNombreVehiculo(tipoVehiculo);
            double subtotal = horasCobradas * tarifaPorHora;
            double descuento = calcularDescuento(subtotal, horasCobradas);

            //sobercarga de metodos
            double totalPagar;
            if (perdioTicket) {

                //llamar version con 3 parametros
                totalPagar = calcularPago(horasCobradas, tarifaPorHora, RECARGO_TICKET_PERDIDO);
                contadorTicketsPerdidos++;
            } else {
                // llamar la version con 2 parametros
                totalPagar = calcularPago(horasCobradas, tarifaPorHora);
            }

            double recargoAplicado = perdioTicket ? RECARGO_TICKET_PERDIDO : 0.0;

            // actualizar estadisticas por tipo de vehiculo
            switch (tipoVehiculo) {
                case 1 -> contadorMotos++;
                case 2 -> contadorAutos++;
                case 3 -> contadorPickups++;
            }

            totalRecaudado += totalPagar;

            // Control del pago más alto
            if (totalPagar > pagoMasAlto) {
                pagoMasAlto = totalPagar;
                placaPagoMasAlto = placa;
                tipoPagoMasAlto = nombreTipo;
            }

            //4 mostrar comprobante

            mostrarComprobante(placa, nombreTipo, horasExactas, minutosRestantes, horasCobradas,
                    tarifaPorHora, subtotal, descuento, recargoAplicado, totalPagar);
        }

        //5 mostrar resumen general de la jornada
        mostrarResumenJornada(contadorMotos, contadorAutos, contadorPickups, contadorTicketsPerdidos,
                totalRecaudado, pagoMasAlto, placaPagoMasAlto, tipoPagoMasAlto);

        scanner.close();
    }

    //metodos , mostrar nombre y carne
    public static void mostrarEncabezado() {
        System.out.println(" SISTEMA DE CONTROL DE PARQUEO");
        System.out.println(" Marino Jeriel Cabrera Mendoza");
        System.out.println(" Carné: 9941-23-8505");
    }

    public static double obtenerTarifa(int tipoVehiculo) {
        return switch (tipoVehiculo) {
            case 1 -> TARIFA_MOTO;
            case 2 -> TARIFA_AUTO;
            case 3 -> TARIFA_PICKUP;
            default -> 0.0;
        };
    }

    public static String obtenerNombreVehiculo(int tipoVehiculo) {
        return switch (tipoVehiculo) {
            case 1 -> "Motocicleta";
            case 2 -> "Automovil";
            case 3 -> "Pickup o camioneta";
            default -> "Desconocido";
        };
    }

    public static double calcularDescuento(double subtotal, int horas) {
        if (horas > 8) {
            return subtotal * PORCENTAJE_DESCUENTO;
        }
        return 0.0;
    }

    //sobrecaraga 1: calcular pago sin recargo
    public static double calcularPago(int horas, double tarifa) {
        double subtotal = horas * tarifa;
        double descuento = calcularDescuento(subtotal, horas);
        return subtotal - descuento;
    }

    //sobrecarga 2; calcular pago con recargo por ticket perdido
    public static double calcularPago(int horas, double tarifa, double recargo) {
        double subtotal = horas * tarifa;
        double descuento = calcularDescuento(subtotal, horas);
        return (subtotal - descuento) + recargo;
    }

    public static void mostrarComprobante(String placa, String tipo, int horasExactas, int minutosRestantes,
                                          int horasCobradas, double tarifa, double subtotal,
                                          double descuento, double recargo, double total) {
        System.out.println("\nCOMPROBANTE");
        System.out.println("Placa: " + placa);
        System.out.println("Tipo: " + tipo);
        System.out.println("Tiempo estacionado: " + horasExactas + " horas y " + minutosRestantes + " minutos");
        System.out.println("Horas cobradas: " + horasCobradas);
        System.out.printf("Tarifa por hora: Q%.2f\n", tarifa);
        System.out.printf("Subtotal: Q%.2f\n", subtotal);
        System.out.printf("Descuento (15%% >8h): Q%.2f\n", descuento);
        System.out.printf("Recargo por ticket perdido: Q%.2f\n", recargo);
        System.out.printf("TOTAL: Q%.2f\n", total);
    }

    public static void mostrarResumenJornada(int motos, int autos, int pickups, int ticketsPerdidos,
                                             double totalRecaudado, double pagoMasAlto,
                                             String placaMasAlta, String tipoMasAlto) {
        System.out.println(" \nRESUMEN GENERAL DE LA JORNADA");
        System.out.println("Cantidad de motocicletas: " + motos);
        System.out.println("Cantidad de automoviles: " + autos);
        System.out.println("Cantidad de pickups o camionetas: " + pickups);
        System.out.println("Total de tickets perdidos: " + ticketsPerdidos);
        System.out.printf("Total de dinero recaudado: Q%.2f\n", totalRecaudado);
        if (pagoMasAlto >= 0) {
            System.out.printf("Pago mas alto realizado: Q%.2f (Placa: %s | Tipo: %s)\n",
                    pagoMasAlto, placaMasAlta, tipoMasAlto);
        } else {
            System.out.println("No se registraron pagos.");
        }
    }

    //metodos reto opcional
    public static int calcularMinutosTranscurridos( int hEntrada, int mEntrada, int hSalida, int mSalida) {
        int totalMinEntrada = (hEntrada * 60) + mEntrada;
        int totalMinSalida = (hSalida * 60) + mSalida;

        if (totalMinSalida < totalMinEntrada) {
            totalMinSalida += (24 * 60);
        }

        return totalMinSalida - totalMinEntrada;
    }

    public static int calcularHorasCobradas(int minutosTranscurridos) {
        if (minutosTranscurridos == 0) return 0;
        return (int) Math.ceil((double) minutosTranscurridos / 60.0);
    }

    //metodos de validacion y entrada de datos
    public static int solicitarEnteroPositivo(Scanner scanner, String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor > 0) break;
            } else {
                scanner.next();
            }
            System.out.println("Error: Debe ingresar un entero mayor que cero.");
        }
        return valor;
    }

    public static int solicitarTipoVehiculo(Scanner scanner) {
        int tipo;
        while (true) {
            System.out.println("\nTipo de vehiculo:");
            System.out.println("1: Motocicleta (Q5.00/h)");
            System.out.println("2: Automovil (Q8.00/h)");
            System.out.println("3: Pickup o camioneta (Q12.00/h)");
            System.out.print("Seleccione una opcion (1-3): ");

            if (scanner.hasNextInt()) {
                tipo = scanner.nextInt();
                if (tipo >= 1 && tipo <= 3) break;
            } else {
                scanner.next();
            }
            System.out.println("Error: Opcion invalida. Seleccionar 1, 2 o 3");
        }
        return tipo;
    }

    public static int solicitarRango(Scanner scanner, String mensaje, int min, int max) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) break;
            } else {
                scanner.next();
            }
            System.out.println("Error: Ingrese un numero entre " + min + " y " + max + ".");
        }
        return valor;
    }

    public static boolean solicitarRespuestaBooleana(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String resp = scanner.next().trim().toUpperCase();
            if (resp.equals("S")) return true;
            if (resp.equals("N")) return false;
            System.out.println("Error: Debe responder unicamente con 'S' o 'N'.");
        }
    }
}
