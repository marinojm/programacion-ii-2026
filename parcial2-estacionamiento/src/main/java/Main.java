import model.Automovil;
import model.Motocicleta;
import model.Vehiculo;
import service.EstacionamientoService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstacionamientoService service = new EstacionamientoService();
        boolean continuar = true;

        System.out.println(" SISTEMA DE GESTION DE ESTACIONAMIENTO ");

        while (continuar) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Mostrar todos los vehículos registrados");
            System.out.println("3. Buscar un vehículo por placa");
            System.out.println("4. Mostrar el vehículo que generó el mayor costo");
            System.out.println("5. Mostrar el total general recaudado");
            System.out.println("6. Mostrar el total recaudado por tipo de vehículo");
            System.out.println("7. Salir");
            System.out.print("Seleccionar una opción: ");

            int opcion = -1;

            try {
                String entrada = scanner.nextLine();
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debe ingresar un número entero valido para la opción.");
            } finally {
                System.out.println("Lectura de opción finalizada.");
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE VEHICULO ---");
                    System.out.println("1. Automóvil (Q10.00/hrs)");
                    System.out.println("2. Motocicleta (Q6.00/hrs)");
                    System.out.print("Seleccione tipo de vehículo: ");
                    String tipo = scanner.nextLine().trim();

                    if (!tipo.equals("1") && !tipo.equals("2")) {
                        System.out.println("Tipo de vehículo inválido.");
                        break;
                    }

                    System.out.print("Ingrese placa: ");
                    String placa = scanner.nextLine().trim();
                    if (placa.isEmpty()) {
                        System.out.println("La placa no puede estar vacía.");
                        break;
                    }

                    System.out.print("Ingrese nombre del propietario: ");
                    String propietario = scanner.nextLine().trim();
                    if (propietario.isEmpty()) {
                        System.out.println("El nombre del propietario no puede estar vacío.");
                        break;
                    }

                    System.out.print("Ingrese hora de ingreso (Hora:Minuto) ");
                    String horaIngreso = scanner.nextLine().trim();

                    double horas = 0.0;
                    boolean horasValidas = false;

                    try {
                        System.out.print("Ingrese cantidad de horas utilizadas: ");
                        horas = Double.parseDouble(scanner.nextLine());
                        if (horas <= 0) {
                            System.out.println("Las horas utilizadas deben ser mayores a cero.");
                        } else {
                            horasValidas = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: La cantidad de horas debe ser un valor numérico válido");
                    } finally {
                        System.out.println("Proceso de captura de horas finalizado.");
                    }

                    if (horasValidas) {
                        service.registrarVehiculo(tipo, placa, propietario, horaIngreso, horas);
                    }
                    break;

                case 2:
                    service.mostrarTodosLosVehiculos();
                    break;

                case 3:
                    System.out.print("\nIngrese la placa a buscar: ");
                    String placaBuscar = scanner.nextLine();
                    Vehiculo en = service.buscarPorPlaca(placaBuscar);
                    if (en != null) {
                        System.out.println("VEHICULO ENCONTRADO:");
                        en.mostrarInformacion();
                    } else {
                        System.out.println("No se encontró ningún vehículo con la placa especificada.");
                    }
                    break;

                case 4:
                    service.mostrarVehiculoMayorCosto();
                    break;

                case 5:
                    System.out.println("\nTOTAL GENERAL RECAUDADO: Q" + String.format("%.2f", service.calcularTotalGeneral()));
                    break;

                case 6:
                    System.out.println("\nTOTAL RECAUDADO POR TIPO DE VEHÍCULO:");
                    HashMap<String, Double> totales = service.obtenerTotalesPorTipo();
                    for (Map.Entry<String, Double> entry : totales.entrySet()) {
                        System.out.println("• " + entry.getKey() + ": Q" + String.format("%.2f", entry.getValue()));
                    }
                    break;

                case 7:
                    continuar = false;
                    System.out.println("\nGracias por utilizar el sistema. ¡Hasta luego!");
                    break;

                default:
                    if (opcion != -1) {
                        System.out.println("Opción inválida. Seleccione un número entre 1 y 7.");
                    }
                    break;
            }
        }
        scanner.close();
    }
}