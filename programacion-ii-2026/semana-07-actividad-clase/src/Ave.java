public class Ave extends Mascota {

    private String tipo;

    public Ave(
            String nombre,
            int edadMeses,
            double pesoKg,
            String tipo,
            String codigoPaciente) {

        super(nombre, edadMeses, pesoKg, codigoPaciente);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void emitirSonido() {
        System.out.println(
                getNombre() + ": ¡Pío, pío!");
    }
    @Override
    public void calcularCosto(){

    }
}