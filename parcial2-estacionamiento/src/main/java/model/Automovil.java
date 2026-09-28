package model;

public class Automovil extends Vehiculo {
    private static final double TARIFA_POR_HORA = 10.00;

    public Automovil(String placa, String propietario, String horaIngreso, double horasUtilizadas) {
        super(placa, propietario, horaIngreso, horasUtilizadas);
    }

    @Override
    public double calcularCosto() {
        double costoBase = getHorasUtilizadas() * TARIFA_POR_HORA;
        if (getHorasUtilizadas() > 5) {
            costoBase -= costoBase * 0.10; // 10% de descuento por permanencia > 5 horas
        }
        return costoBase;
    }

    @Override
    public String getTipo() {
        return "Automovil";
    }
}

