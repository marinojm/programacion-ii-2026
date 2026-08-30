public class Triangulo {
    private double base;
    private double altua;

    public Triangulo(String nombre, double base, double altura) {
        super(nombre);
        this.base = altura;
        this.altura = altura;

    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2.0;
    }

}
