public class Ave extends Mascota {

    private String tipo;

    public Ave(
            String nombre,
            int edadMeses,
            double pesoKg,
            String tipo) {

        super(nombre, edadMeses, pesoKg);
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
}