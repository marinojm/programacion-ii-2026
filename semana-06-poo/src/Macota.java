public abstract class Mascota {

    private String codigoPaciente;
    private String nombre;
    private int edadMeses;
    private double pesoKg;

    public Mascota(
            String codigoPaciente,
            String nombre,
            int edadMeses,
            double pesoKg) {

        this.codigoPaciente = codigoPaciente;
        this.nombre = nombre;
        this.edadMeses = edadMeses;
        this.pesoKg = pesoKg;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdadMeses() {
        return edadMeses;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void actualizarPeso(double nuevoPeso) {
        if (nuevoPeso > 0) {
            this.pesoKg = nuevoPeso;
        } else {
            System.out.println(
                    "El peso no es válido.");
        }
    }

    public void cumplirMes() {
        edadMeses++;
    }

    public String mostrarInformacion() {
        return codigoPaciente + " | "
                + nombre + " | "
                + edadMeses + " meses | "
                + pesoKg + " kg";
    }

    public abstract void emitirSonido();

    public abstract double calcularCostoConsulta();
}