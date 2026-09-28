public class Perro extends Mascota {

    private String raza;

    public Perro(
            String codigoPaciente,
            String nombre,
            int edadMeses,
            double pesoKg,
            String raza) {

        super(
                codigoPaciente,
                nombre,
                edadMeses,
                pesoKg
        );

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
    public double calcularCostoConsulta() {
        double costo = 100.00;

        if (getPesoKg() > 25.0) {
            costo += 25.00;
        }

        return costo;
    }
}