public class EnvioNacional extends Envio {
    private String departamentoDestino;
    private double distanciaKm;

    public EnvioNacional(String codigoEnvio, String nombreDestinatario, double pesoKg, String departamentoDestino, double distanciaKm) {
        super(codigoEnvio, nombreDestinatario, pesoKg);
        this.departamentoDestino = departamentoDestino;
        this.distanciaKm = distanciaKm;
    }

    public String getDepartamentoDestino() {
        return departamentoDestino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    //Override del calculo del costo final

    @Override
    public double calcularCostoFinal() {
        double cargoDistancia = distanciaKm * 0.50;
        return calcularCostoBase() + cargoDistancia;
    }

    @Override
    public void mostrarResumen(boolean mostrarDesglose) {
        if (!mostrarDesglose) {
            super.mostrarResumen();
            return;
        }

        super.mostrarResumen(true);
        double cargoDistancia = distanciaKm - 0.50;
        System.out.println("Tipo: Evio Nacional");
        System.out.println("Departamento destino: " + departamentoDestino);
        System.out.printf("Distancia: %.2f km\n", cargoDistancia);
        System.out.printf("COSTO FINAL: Q%.2f km\n", calcularCostoFinal());
    }
}
