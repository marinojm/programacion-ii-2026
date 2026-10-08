package com.estudiante.controldespensa.controller;

import com.estudiante.controldespensa.model.Producto;
import com.estudiante.controldespensa.model.ResumenInventario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Arroz Precocido", "Granos", 10, 12.50));  // Subtotal = 125.00
        productos.add(new Producto(2L, "Frijol Negro", "Granos", 2, 9.00));       // Subtotal = 18.00 (Stock Bajo <= 3)
        productos.add(new Producto(3L, "Leche Entera", "Lácteos", 5, 16.50));     // Subtotal = 82.50
        productos.add(new Producto(4L, "Queso Crema", "Lácteos", 1, 24.00));      // Subtotal = 24.00 (Stock Bajo <= 3)
        productos.add(new Producto(5L, "Detergente en Polvo", "Limpieza", 3, 45.00)); // Subtotal = 135.00 (Stock Bajo <= 3) -> Mayor valor!
        productos.add(new Producto(6L, "Jabón de Baño", "Higiene", 8, 7.50));     // Subtotal = 60.00
    }

    // 1 GET /api/productos - Lista todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productos;
    }

    // 2 GET /api/productos/id - Busca por identificador
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        Optional<Producto> encontrado = productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return encontrado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3 GET /api/productos/categoria/categoria - Busca por categoria
    @GetMapping("/categoria/{categoria}")
    public List<Producto> buscarPorCategoria(@PathVariable String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    // 4 GET /api/productos/stock-bajo - Consultar stock bajo
    @GetMapping("/stock-bajo")
    public List<Producto> obtenerStockBajo() {
        return productos.stream()
                .filter(p -> p.getCantidad() <= 3)
                .toList();
    }

    // 5 GET /api/productos/mayor-valor - consultar el producto de mayor subtotal
    @GetMapping("/mayor-valor")
    public ResponseEntity<Producto> obtenerMayorValor() {
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto mayor = productos.get(0);
        for (Producto p : productos) {
            if (p.calcularSubtotal() > mayor.calcularSubtotal()) {
                mayor = p;
            }
        }
        return ResponseEntity.ok(mayor);
    }

    // 6 GET /api/productos/resumen - Obtiene el resumen general del inventario
    @GetMapping("/resumen")
    public ResumenInventario obtenerResumen() {
        int cantidadProductos = productos.size();
        int totalUnidades = 0;
        double valorTotal = 0.0;

        for (Producto p : productos) {
            totalUnidades += p.getCantidad();
            valorTotal += p.calcularSubtotal();
        }

        return new ResumenInventario(cantidadProductos, totalUnidades, valorTotal);
    }

}
