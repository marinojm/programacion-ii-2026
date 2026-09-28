package service;

import model.Automovil;
import model.Motocicleta;
import model.Vehiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EstacionamientoService {
    private ArrayList<Vehiculo> vehiculos;
    private HashSet<String> placasRegistradas;

    public EstacionamientoService() {
        this.vehiculos = new ArrayList<>();
        this.placasRegistradas = new HashSet<>();
    }

    public boolean registrarVehiculo(String tipo, String placa, String propietario, String horaIngreso, double horas) {
        String placaFormateada = placa.trim().toUpperCase();

        if (placasRegistradas.contains(placaFormateada)) {
            System.out.println("ERROR: La placa '" + placaFormateada + "' ya se encuentra registrada en el sistema.");
            return false;
        }

        Vehiculo nuevoVehiculo;
        if (tipo.equalsIgnoreCase("1") || tipo.equalsIgnoreCase("Automovil")) {
            nuevoVehiculo = new Automovil(placaFormateada, propietario, horaIngreso, horas);
        } else if (tipo.equalsIgnoreCase("2") || tipo.equalsIgnoreCase("Motocicleta")) {
            nuevoVehiculo = new Motocicleta(placaFormateada, propietario, horaIngreso, horas);
        } else {
            System.out.println("ERROR: Tipo de vehiculo no valido.");
            return false;
        }

        vehiculos.add(nuevoVehiculo);
        placasRegistradas.add(placaFormateada);
        System.out.println("Vehiculo registrado exitosamente.");
        return true;
    }

    public void mostrarTodosLosVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el estacionamiento.");
            return;
        }
        System.out.println("\nLISTADO DE VEHICULOS REGISTRADOS");
        for (Vehiculo v : vehiculos) {
            v.mostrarInformacion();
        }
    }

    public Vehiculo buscarPorPlaca(String placa) {
        String placaBusqueda = placa.trim().toUpperCase();
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placaBusqueda)) {
                return v;
            }
        }
        return null;
    }

    public void mostrarVehiculoMayorCosto() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados para evaluar.");
            return;
        }

        Vehiculo mayor = vehiculos.get(0);
        for (Vehiculo v : vehiculos) {
            if (v.calcularCosto() > mayor.calcularCosto()) {
                mayor = v;
            }
        }

        System.out.println("\nVEHICULO CON MAYOR COSTO GENERADO:");
        mayor.mostrarInformacion();
    }

    public double calcularTotalGeneral() {
        double total = 0.0;
        for (Vehiculo v : vehiculos) {
            total += v.calcularCosto();
        }
        return total;
    }

    public HashMap<String, Double> obtenerTotalesPorTipo() {
        HashMap<String, Double> totales = new HashMap<>();
        totales.put("Automovil", 0.0);
        totales.put("Motocicleta", 0.0);

        for (Vehiculo v : vehiculos) {
            String tipo = v.getTipo();
            double acumuladoActual = totales.getOrDefault(tipo, 0.0);
            totales.put(tipo, acumuladoActual + v.calcularCosto());
        }

        return totales;
    }
}