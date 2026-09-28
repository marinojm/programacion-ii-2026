import java.util.Scanner;
public class DesafiosLogicos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        //Encabezado de personalizacion
        System.out.println("Estudiante: Marino Jeriel Cabrera Mendoza");
        System.out.println("Carne: 9941-23-8505");
        System.out.println("Semana 3 - condiciones y ciclos");

        //ciclo do while para controlar el menu principal
        do {
        System.out.println("1. Generar una secuencia");
        System.out.println("2. Realizar un conteo reresivo");
        System.out.println("3. Analizar números");
        System.out.println("4. Dibujar una pirámide");
        System.out.println("5. Validar palabra secreta");
        System.out.println("Seleccione una opción: ");

        opcion = scanner.nextInt();

        //estructura switch para procesar las opciones del menu
        switch (opcion) {

            //generar una secuencia
            case 1:
                System.out.println("\n GENERAR SECUENCIA ");
                System.out.println("ingrese numero inicial: ");
                int numInicio = scanner.nextInt();

                System.out.println("ingrese numero final: ");
                int numFinal = scanner.nextInt();

                System.out.println("ingrese incremento: ");
                int incremento = scanner.nextInt();

                //validacion mediante if / else
                if (incremento <= 0) {
                    System.out.println("Error: el incremento debe ser mayor que cero");
                } else if (numFinal <= numInicio) {
                    System.out.println("Error: el numero final debe ser mayor que el inicial");
                } else {
                    System.out.print("resultado: ");

                    //ciclo for para la secuencia
                    for (int i = numInicio; i <= numFinal; i += incremento) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }
                break;

            //opcion realizar un conteo regresivo
            case 2:
                System.out.println("\n CONTEO REGRESIVO ");
                System.out.println("ingrese el numero inicial entre 10 y 50: ");
                int numConteo = scanner.nextInt();

                //ciclo while para pedir el numero de nuevo si es que no cumple con el rango
                while (numConteo < 10 || numConteo > 50) {
                    System.out.println("numero fuera de rango. Debe estar entre 10 y 50");
                    System.out.print("ingrese el numero inicial nuevamente: ");
                    numConteo = scanner.nextInt();
                }

                System.out.print("Resultado: ");
                //ciclo while para el conteo regresivo
                while (numConteo >= 0) {
                    System.out.print(numConteo + " ");
                    numConteo--;
                }
                System.out.println("\n Despegue");
                break;

            case 3:
                System.out.println("\nANALIZAR NUMEROS");
                int positivos = 0;
                int negativos = 0;
                int sumaValida = 0;
                int ignorados = 0;

                // ciclo while para lectura de numeros indefinida
                while (true) {
                    System.out.print("ingrese un numero: ");
                    int num = scanner.nextInt();

                    if (num == 0) {
                        break;
                    }

                    if (num % 5 == 0) {
                    System.out.println("El numero " + num + " fue ignorado");
                    ignorados++;
                    continue;
                    }

                    if (num > 0) {
                        positivos++;
                    } else {
                        negativos++;
                    }
                    sumaValida += num;
                }

                System.out.println("\nResultado: ");
                System.out.println("positivos: " + positivos);
                System.out.println("negativos: " + negativos);
                System.out.println("suma valida:" +sumaValida);
                System.out.println("numeros ignorados:" + ignorados);
                break;

            case 4:
                System.out.println("\n DIBUJAR PIRÁMIDE ");
                System.out.println("\n ingrese la altura. Debe ser entre 3 y 10: ");
                int altura = scanner.nextInt();

                //validacion de la altua mediante ciclo while
                while (altura < 3 || altura > 10) {
                    System.out.println("Altura invalida. debe estar entre 3 y 10");
                    System.out.print("ingrese la altura nuevamente");
                    altura = scanner.nextInt();
                }

                System.out.println("Resultado: ");
                //ciclos for anidado para dibujar la piramide
                for (int i = 1; i <= altura; i++) {
                    //ciclo interno 1: imprime espacios en blanco
                    for (int j = 1; j <= altura - i; j++) {
                        System.out.print(" ");
                    }
                    //ciclo interno 2: imprimir asteriscos
                    for (int k = 1; k <= (2 * i -1); k++){
                        System.out.print("*");
                    }
                    //saltar la linea por cada nivel de la piramide
                    System.out.println();
                }
                break;

            case 5:
                System.out.println("\n VALIDAR PALABRA SECRETA ");
                //LIMPIEZA DEL BUFFER DEL SCANNER ANTES DE LEER LINEAS DE TEXTO
                scanner.nextLine();

                String intento = "";
                String palabraObjetivo = "Guatemala";

                //ciclo do-while para pedir la palabra hasta que sea correcta
                do {
                    System.out.print("ingrese la palabra secreta: ");
                    intento = scanner.nextLine();

                    //eliminar espacios en extremos
                    if (intento.trim().equalsIgnoreCase(palabraObjetivo)) {
                        System.out.println("palabra correcta");
                        break;
                    } else {
                        System.out.println("palabra incorrecta. intente de nuevo \n");
                    }
                } while (true);
                break;

            //salir del programa
            case 6:
                System.out.println("\n PROGRAMA FINALIZADO CORRECTAMENTE ");
                break;

                //validacion de opcion no existente en el menu
            default:
                System.out.println("\nopcion no valida. Intente de nuevo");
                break;

        }

        System.out.println(); //espacio para separar repeticiones del menu

    } while (opcion != 6);

    scanner.close();

    }
}
