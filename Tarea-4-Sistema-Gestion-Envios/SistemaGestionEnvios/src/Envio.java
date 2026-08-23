public class Envio {
    private String codigoEnvio;
    private String nombreDestinatario;
    private double pesoKg;

    public Envio(String codigoEnvio, String nombreDestinatario, double pesoKg) {
       this.codigoEnvio = codigoEnvio;
       this.nombreDestinatario = nombreDestinatario;
       this.pesoKg = pesoKg;
    }

    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public double getPeso() {
        return pesoKg;
    }

    public double calcularCostoBase() {
        return this.pesoKg * 10.00;
    }

    public double calcularCostoFinal() {
        return calcularCostoBase();
    }

    //overflow 1: resumen
    public void mostrarResumen() {
        System.out.println("RESUMEN GENERAL DE ENVIO");
        System.out.println("Codigo: " + codigoEnvio);
        System.out.println("Destinatario: " + nombreDestinatario);
        System.out.printf("Costo Final: Q%.2f\n", + calcularCostoFinal());
    }

    //overflow 2: recibir bool para mostrar dsglose completo o sencilla
    public void mostrarResumen(boolean mostrarDesglose) {
        if (!mostrarDesglose) {
            mostrarResumen();
            return;
        }

        System.out.println("DESGLOSE DETALLADO DEL ENVIO");
        System.out.println("Codigo: " + codigoEnvio);
        System.out.println("Destinatario: " + nombreDestinatario);
        System.out.printf("Peso del paquete: %.2f kg\n", pesoKg);
        System.out.printf("Costo base (Q10.00/kg): Q%.2f\n", calcularCostoBase());
    }
}
