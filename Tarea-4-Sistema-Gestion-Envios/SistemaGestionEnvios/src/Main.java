import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarEncabezado();
            System.out.println("SISTEMA DE ENVIOS");
            System.out.println("1. Registrar envio nacional");
            System.out.println("2. Registrar envio internacional");
            System.out.println("3. Salir");
            int opcion = solicitarEnteroRango(scanner, "Seleccione una opcion (1-3): ", 1, 3);

            if (opcion == 3) {
                System.out.println("\nGracias por utilizar el Sistema de Envios.");
                continuar = false;
                break;
            }

            System.out.println("\nREGISTRO DE DATOS GENERALES");
            String codigo = solicitarTextoNoVacio(scanner, "Ingrese código del envío: ");
            String destinatario = solicitarTextoNoVacio(scanner, "Ingrese nombre del destinatario: ");
            double peso = solicitarDoublePositivo(scanner, "Ingrese peso del paquete en kg: ");

            //variable polimorfica

            Envio envio;

            if (opcion == 1) {
                System.out.println("\nDATOS ENVIO NACIONAL");
                String departamento = solicitarTextoNoVacio(scanner, "Ingrese departamento de destino: ");
                double distancia = solicitarDoublePositivo(scanner, "Ingrese distancia km: ");

                //clase hija
                envio = new EnvioNacional(codigo, destinatario, peso, departamento, distancia);
            } else {
                System.out.println("\n--- DATOS ENVÍO INTERNACIONAL ---");
                String pais = solicitarTextoNoVacio(scanner, "Ingrese país de destino: ");

                envio = new EnvioInternacional(codigo, destinatario, peso, pais);
            }
                envio.mostrarResumen(true);

                String respuesta = solicitarConfirmacion(scanner, "\n¿Desea registrar otro envío? (S/N): ");
                if (respuesta.equalsIgnoreCase("N")) {
                    continuar = false;
                    System.out.println("\nGracias por utilizar el Sistema de Envios.");
                }
                System.out.println();
            }

            scanner.close();
        }

        private static void mostrarEncabezado() {

            System.out.println(" Sistema de Calculo de Envios - POO");
            System.out.println(" Estudiante: Marino Jeriel Cabrera Mendoza");
            System.out.println(" Carné: 9941-23-8505");
        }

        //metodos de validacion
        private static String solicitarTextoNoVacio(Scanner scanner, String mensaje) {
            String entrada;
            while (true) {
                System.out.print(mensaje);
                entrada = scanner.nextLine().trim();
                if (!entrada.isEmpty()) {
                    return entrada;
                }
                System.out.println("Error: El campo no puede estar vacio.");
            }
        }

        private static double solicitarDoublePositivo(Scanner scanner, String mensaje) {
            double valor;
            while (true) {
                System.out.print(mensaje);
                if (scanner.hasNextDouble()) {
                    valor = scanner.nextDouble();
                    scanner.nextLine(); // limpiar buffer
                    if (valor > 0) {
                        return valor;
                    }
                } else {
                    scanner.nextLine(); // limpiar entrada invalida
                }
                System.out.println("Error: Debe ingresar un numero mayor a cero.");
            }
        }

        private static int solicitarEnteroRango(Scanner scanner, String mensaje, int min, int max) {
            int valor;
            while (true) {
                System.out.print(mensaje);
                if (scanner.hasNextInt()) {
                    valor = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    if (valor >= min && valor <= max) {
                        return valor;
                    }
                } else {
                    scanner.nextLine(); // Limpiar entrada inválida
                }
                System.out.println("Error: seleccione una opcion valida entre " + min + " y " + max + ".");
            }
        }

        private static String solicitarConfirmacion(Scanner scanner, String mensaje) {
            String resp;
            while (true) {
                System.out.print(mensaje);
                resp = scanner.nextLine().trim().toUpperCase();
                if (resp.equals("S") || resp.equals("N")) {
                    return resp;
                }
                System.out.println("Error: Ingrese únicamente 'S' o 'N'.");
            }
        }
    }
