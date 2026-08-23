public class Gato extends Mascota {

    private boolean esInterior;

    public Gato(
            String nombre,
            int edadMeses,
            double pesoKg,
            boolean esInterior,
            String codigoPaciente) {

        super(nombre, edadMeses, pesoKg, codigoPaciente);
        this.esInterior = esInterior;
    }

    public boolean isEsInterior() {
        return esInterior;
    }

    @Override
    public void emitirSonido() {
        System.out.println(
                getNombre() + ": ¡Miau!");
    }
    @Override
    public void calcularCosto() {

    }

}