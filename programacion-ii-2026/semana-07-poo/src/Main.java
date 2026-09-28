import java.util.HashMap;
import java.util.HashSet;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA VETERINARIO VETCARE ===");

        // Arreglo polimórfico:
        // una referencia Mascota puede almacenar
        // objetos Perro, Gato o Ave.
        HashMap<String, Mascota> pacientes = new HashMap<>();
               Mascota luna = new Perro(
                        "VET-001",
                        "Luna",
                        14,
                        24.5,
                        "Mestiza"
                );
                Mascota milo = new Gato(
                        "VET-002",
                        "Milo",
                        24,
                        4.8,
                        true
                );
                Mascota piolin = new Ave(
                        "VET-003",
                        "Piolín",
                        10,
                        0.4,
                        "Exótica"
                );


        System.out.println("\n=== PACIENTES REGISTRADOS ===");

        // Recorrido del arreglo
//        for (Mascota paciente : pacientes) {
//            System.out.println("\n-----------------------------");
//            System.out.println(paciente.mostrarInformacion());
//
//            // Ejecuta la versión correspondiente
//            // al objeto real: Perro, Gato o Ave.
//            paciente.emitirSonido();
//
//            // También ejecuta el cálculo especializado
//            double costo = paciente.calcularCostoConsulta();
//
//            System.out.println("Costo de consulta: Q" + costo);
//        }
//
//        System.out.println("\n=== ACTUALIZACIÓN DE PESO ===");
//
//        // Luna inicia con 24.5 kg.
//        // Su consulta cuesta inicialmente Q100.
//        System.out.println("Peso anterior de Luna: "+ pacientes[0].getPesoKg() + " kg");
//        System.out.println("Costo anterior: Q"+ pacientes[0].calcularCostoConsulta());
//
//        // El peso se modifica mediante un método
//        // porque el atributo es privado.
//        pacientes[0].actualizarPeso(30.0);
//
//        System.out.println("\nInformación actualizada:");
//        System.out.println(pacientes[0].mostrarInformacion());
//
//        // Como ahora pesa más de 25 kg,
//        // el costo cambia de Q100 a Q125.
//        System.out.println("Nuevo costo: Q"+ pacientes[0].calcularCostoConsulta());
//
//
            pacientes.put(luna.getCodigoPaciente(), luna);

            String codigoPaciente = "VET-001";
            if (pacientes.containsKey(codigoPaciente)){
                Mascota encontrada = pacientes.get(codigoPaciente);
            System.out.println(encontrada.mostrarInformacion());
            }else{
                System.out.println("Paciente no econtrador");
            }

    }
}