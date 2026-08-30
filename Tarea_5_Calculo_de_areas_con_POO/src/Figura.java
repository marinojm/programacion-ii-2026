public abstract class Figura {
    private String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarInfotmacion() {
        System.out.println("Figura: " + nombre);
    }

    public abstract double calcularArea();
}
