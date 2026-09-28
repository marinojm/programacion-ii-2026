package model;

public abstract class Vehiculo {
    private String placa;
    private String propietario;
    private String horaIngreso;
    private double horasUtilizadas;

    public Vehiculo(String placa, String propietario, String horaIngreso, double horasUtilizadas) {
        this.placa = placa;
        this.propietario = propietario;
        this.horaIngreso = horaIngreso;
        this.horasUtilizadas = horasUtilizadas;
    }

    public String getPlaca() {
        return placa;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public double getHorasUtilizadas() {
        return horasUtilizadas;
    }

    //metodo obligatorio para sobreescritura
    public abstract double calcularCosto();

    //metodo abstracto para identificar el tipo de vehiculo
    public abstract String getTipo();

    //metodo para mostrar la informacion general del vehiculo
    public void mostrarInformacion() {
        System.out.println("----------------------------------------");
        System.out.println("Tipo: " + getTipo());
        System.out.println("Placa: " + placa);
        System.out.println("Propietario: " + propietario);
        System.out.println("Hora de Ingreso: " + horaIngreso);
        System.out.println("Horas Utilizadas: " + horasUtilizadas);
        System.out.println("Costo Calculado: Q" + String.format("%.2f", calcularCosto()));
        System.out.println("----------------------------------------");
    }
}