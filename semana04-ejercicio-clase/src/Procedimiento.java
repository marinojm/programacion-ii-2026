public class Procedimiento {
    public static void main(String[] args) {
        mostrarSeparador();
        saludar("");
        mostrarSeparador();
    }
    public static void saludar(String nombre) {
        System.out.println("Bienvenida" + nombre);
    }
    public static void mostrarSeparador() {
        System.out.println("----");
    }
}
