public class Perro extends Mascota {

    private String raza;

    public Perro(
            String nombre,
            int edadMeses,
            double pesoKg,
            String raza,
            String codigoPaciente) {

        super(nombre, edadMeses, pesoKg, codigoPaciente);
        this.raza = raza;
    }

    public String getRaza() {
        return raza;
    }

    @Override
    public void emitirSonido() {
        System.out.println(
                getNombre() + ": ¡Guau!");
    }

    @Override
    public void calcularCosto(){

    }
}