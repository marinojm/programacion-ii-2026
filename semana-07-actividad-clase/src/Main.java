public class Main {

    public static void main(String[] args) {

        Mascota mascota1 = new Perro(
                "Luna",
                14,
                12.5,
                "Mestiza",
                "1234");

        Mascota mascota2 = new Gato(
                "Milo",
                24,
                4.8,
                true,
                "7852");

        System.out.println(
                mascota1.mostrarInformacion());

        mascota1.emitirSonido();

        System.out.println();

        System.out.println(
                mascota2.mostrarInformacion());

        mascota2.emitirSonido();

        System.out.println();

        mascota1.actualizarPeso(-10);

        Mascota mascota3 = new Ave(
                "Piolín",
                10,
                0.4,
                "Canario",
                "4465");

        System.out.println(
                mascota3.mostrarInformacion());

        mascota3.emitirSonido();
    }
}