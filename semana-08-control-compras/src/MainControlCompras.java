import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class MainControlCompras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Producto> listaProductos = new ArrayList<>();
        HashSet<String> setCategorias = new HashSet<>();
        HashMap<String, Double> mapTotalesCategoria = new HashMap<>();

        System.out.println("REGISTRO DE COMPRAS DEL HOGAR");
        System.out.println("Debe registrar un mínimo de 5 productos válidos. \n");

        int productosValidos = 0;
        int intento = 1;

        while (productosValidos < 5) {
            System.out.println(" Producto #" + intento + " ---");
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine().trim();

            System.out.print("Precio unitario: ");
            double precioUnitario = Double.parseDouble(scanner.nextLine());

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            //validaciones logicas
            if (nombre.isEmpty()) {
                System.out.println("Producto no registrado: el nombre no puede estar vacio.\n");
            } else if (categoria.isEmpty()) {
                System.out.println("Producto no registrado: la categoría no puede estar vacia.\n");
            } else if (precioUnitario <= 0) {
                System.out.println("Producto no registrado: el precio debe ser mayor que cero.\n");
            } else if (cantidad <= 0) {
                System.out.println("Producto no registrado: la cantidad debe ser mayor que cero\n");
            } else {

                //instacias y almacenamiento en colecciones
                Producto producto = new Producto(nombre, categoria, precioUnitario, cantidad);
                listaProductos.add(producto);
                setCategorias.add(categoria);

                double subtotalActual = producto.calcularSubtotal();
                double totalAcumulado = mapTotalesCategoria.getOrDefault(categoria, 0.0);
                mapTotalesCategoria.put(categoria, totalAcumulado + subtotalActual);

                productosValidos++;
                System.out.println("Producto registrado exitosamente!\n");
            }
            intento++;
        }

        //procesamiento e informes
        System.out.println("\n===== RESUMEN DE COMPRAS =====");
        double totalGeneral = 0.0;
        Producto productoMayorGasto = null;
        Producto productoMenorGasto = null;

        for (Producto p : listaProductos) {
            double subtotal = p.calcularSubtotal();
            totalGeneral += subtotal;

            System.out.printf("%s | %s | Q%.2f x %d | Subtotal: Q%.2f%n",
                    p.getNombre(), p.getCategoria(), p.getPrecioUnitario(), p.getCantidad(), subtotal);

            if (productoMayorGasto == null || subtotal > productoMayorGasto.calcularSubtotal()) {
                productoMayorGasto = p;
            }
            if (productoMenorGasto == null || subtotal < productoMenorGasto.calcularSubtotal()) {
                productoMenorGasto = p;
            }
        }

        System.out.println("\nCategorías registradas:");
        System.out.println(setCategorias);

        System.out.println("\nTotal por categoría:");
        String categoriaMayorGasto = "";
        double maxGastoCategoria = -1.0;

        for (Map.Entry<String, Double> entry : mapTotalesCategoria.entrySet()) {
            System.out.printf("%s: Q%.2f%n", entry.getKey(), entry.getValue());
            if (entry.getValue() > maxGastoCategoria) {
                maxGastoCategoria = entry.getValue();
                categoriaMayorGasto = entry.getKey();
            }
        }

        System.out.println("\nProductos registrados: " + listaProductos.size());
        System.out.printf("Total general: Q%.2f%n", totalGeneral);

        if (productoMayorGasto != null) {
            System.out.printf("%nProducto con mayor gasto:%n%s - Q%.2f%n",
                    productoMayorGasto.getNombre(), productoMayorGasto.calcularSubtotal());
        }

        if (productoMenorGasto != null) {
            System.out.printf("%nProducto con menor gasto:%n%s - Q%.2f%n",
                    productoMenorGasto.getNombre(), productoMenorGasto.calcularSubtotal());
        }

        if (!categoriaMayorGasto.isEmpty()) {
            System.out.printf("%nCategoría con mayor gasto:%n%s - Q%.2f%n",
                    categoriaMayorGasto, maxGastoCategoria);
        }

        //consulta de categoria
        System.out.print("Ingrese una categoría para consultar: ");
        String consulta = scanner.nextLine().trim();
        if (mapTotalesCategoria.containsKey(consulta)) {
            System.out.printf("Total gastado en %s: Q%.2f%n", consulta, mapTotalesCategoria.get(consulta));
        } else {
            System.out.println("La categoría ingresada no se encuentra registrada.");
        }

        scanner.close();
    }
}
