package com.umg.reservas.reservasalones.controller;

import com.umg.reservas.reservasalones.dto.SalonDto;
import com.umg.reservas.reservasalones.entity.Salon;
import com.umg.reservas.reservasalones.service.SalonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salones")
@CrossOrigin(origins = "*")
public class SalonController {

    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping
    public ResponseEntity<List<Salon>> listarSalones() {
        return ResponseEntity.ok(salonService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salon> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salonService.obtenerPorId(id));
    }

    // Filtro: Buscar salones por capacidad mínima
    @GetMapping("/capacidad/{capacidad}")
    public ResponseEntity<List<Salon>> buscarPorCapacidad(@PathVariable Integer capacidad) {
        return ResponseEntity.ok(salonService.buscarPorCapacidad(capacidad));
    }

    @PostMapping
    public ResponseEntity<Salon> crearSalon(@Valid @RequestBody SalonDto dto) {
        return new ResponseEntity<>(salonService.crearSalon(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salon> actualizarSalon(@PathVariable Long id, @Valid @RequestBody SalonDto dto) {
        return ResponseEntity.ok(salonService.actualizarSalon(id, dto));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<String> desactivarSalon(@PathVariable Long id) {
        salonService.cambiarEstado(id, false);
        return ResponseEntity.ok("Salón desactivado correctamente.");
    }
}