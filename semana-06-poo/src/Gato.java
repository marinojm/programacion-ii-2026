public class Gato extends Mascota {

    private boolean esInterior;

    public Gato(
            String codigoPaciente,
            String nombre,
            int edadMeses,
            double pesoKg,
            boolean esInterior) {

        super(
                codigoPaciente,
                nombre,
                edadMeses,
                pesoKg
        );

        this.esInterior = esInterior;
    }

    public boolean isInterior() {
        return esInterior;
    }

    @Override
    public void emitirSonido() {
        System.out.println(
                getNombre() + ": ¡Miau!");
    }

    @Override
    public double calcularCostoConsulta() {
        double costo = 90.00;

        if (!esInterior) {
            costo += 15.00;
        }

        return costo;
    }
}