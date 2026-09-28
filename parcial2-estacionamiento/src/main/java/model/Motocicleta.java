package model;

public class Motocicleta extends Vehiculo {
    private static final double TARIFA_POR_HORA = 6.00;

    public Motocicleta(String placa, String propietario, String horaIngreso, double horasUtilizadas) {
        super(placa, propietario, horaIngreso, horasUtilizadas);
    }

    @Override
    public double calcularCosto() {
        double costoBase = getHorasUtilizadas() * TARIFA_POR_HORA;
        if (getHorasUtilizadas() > 5) {
            costoBase -= costoBase * 0.10;
        }
        return costoBase;
    }

    @Override
    public String getTipo() {
        return "Motocicleta";
    }
}