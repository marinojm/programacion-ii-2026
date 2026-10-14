package com.umg.biblioteca.bibliotecaapi.controller;

import com.umg.biblioteca.bibliotecaapi.entity.Libro;
import com.umg.biblioteca.bibliotecaapi.repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository repositorio;

    //inyeccion de dependencias por constructor
    public LibroController(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    //listar todos los libros GET /api/libros
    @GetMapping
    public List<Libro> listar() {
        return repositorio.findAll();
    }

    //buscar un libro por ID /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Registrar un libro (POST /api/libros)
    @PostMapping
    public ResponseEntity<Libro> registrar(@RequestBody Libro libro) {
        libro.setId(null); // Asegura que se cree un nuevo registro
        Libro guardado = repositorio.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    //Actualizar completamente un libro (PUT /api/libros/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro datosNuevos) {
        return repositorio.findById(id)
                .map(libro -> {
                    libro.setCodigo(datosNuevos.getCodigo());
                    libro.setTitulo(datosNuevos.getTitulo());
                    libro.setAutor(datosNuevos.getAutor());
                    libro.setCategoria(datosNuevos.getCategoria());
                    libro.setAnioPublicacion(datosNuevos.getAnioPublicacion());
                    libro.setPrecio(datosNuevos.getPrecio());
                    libro.setDisponible(datosNuevos.getDisponible());

                    Libro actualizado = repositorio.save(libro);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Eliminar un libro (DELETE /api/libros/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Buscar por categoría (GET /api/libros/categoria/{categoria})
    @GetMapping("/categoria/{categoria}")
    public List<Libro> buscarPorCategoria(@PathVariable String categoria) {
        return repositorio.findByCategoria(categoria);
    }

    //Listar libros disponibles (GET /api/libros/disponibles)
    @GetMapping("/disponibles")
    public List<Libro> listarDisponibles() {
        return repositorio.findByDisponibleTrue();
    }
}
