public class EnvioInternacional extends Envio {
    private String paisDestino;
    private static final double CARGO_GESTION_FIJO = 75.00;
    private static final double PORCENTAJE_RECARGO = 0.12;

    public EnvioInternacional(String codigoEnvio, String nombreDestinatario, double pesoKg, String paisDestino) {
        super(codigoEnvio, nombreDestinatario, pesoKg);
        this.paisDestino = paisDestino;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    //Override del calculodel costo final
    @Override
    public double calcularCostoFinal () {
        double costoBase = calcularCostoBase();
        double recargo = costoBase * PORCENTAJE_RECARGO;
        return costoBase + CARGO_GESTION_FIJO + recargo;
    }

    //Override con desglose para incluir datos especificos
    @Override
    public void mostrarResumen(boolean mostrarDesglose) {
        if (!mostrarDesglose) {
            super.mostrarResumen();
            return;
    }

        super.mostrarResumen(true);
        double costoBase = calcularCostoBase();
        double recargo = costoBase * PORCENTAJE_RECARGO;

        System.out.println("Tipo: Envio Internacional");
        System.out.println("Pais destino:" + paisDestino);
        System.out.printf("Cargo fijo gestion internacional: Q%.2f\n", CARGO_GESTION_FIJO);
        System.out.printf("Recargo internacional (12%%): Q%.2f\n", recargo);
        System.out.printf("COSTO FINAL: Q%.2f\n", calcularCostoFinal());
    }
}