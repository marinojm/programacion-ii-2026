package com.umg.biblioteca.bibliotecaapi.repository;

import com.umg.biblioteca.bibliotecaapi.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    //metodo derivado para buscar libros por categoria
    List<Libro> findByCategoria(String categoria);

    //metodo drivado para consultar unicamente los libros disponibles
    List<Libro> findByDisponibleTrue();
}
