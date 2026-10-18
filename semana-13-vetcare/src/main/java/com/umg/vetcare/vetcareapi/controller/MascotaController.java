package com.umg.vetcare.vetcareapi.controller;

import com.umg.vetcare.vetcareapi.entity.Mascota;
import com.umg.vetcare.vetcareapi.repository.MascotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    private final MascotaRepository repositorio;

    public MascotaController(MascotaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Mascota> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> buscar(@PathVariable Long id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @PostMapping
    public ResponseEntity<Mascota> registrar(@RequestBody Mascota mascota) {
        mascota.setId(null);
        Mascota guardada = repositorio.save(mascota);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota datos) {
        return repositorio.findById(id)
                .map(mascota -> {
                    mascota.setCodigo(datos.getCodigo());
                    mascota.setNombre(datos.getNombre());
                    mascota.setEspecie(datos.getEspecie());
                    mascota.setEdadMeses(datos.getEdadMeses());
                    mascota.setPeso(datos.getPeso());
                    mascota.setActiva(datos.getActiva());
                    Mascota actualizada = repositorio.save(mascota);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        repositorio.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}