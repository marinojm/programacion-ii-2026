package com.umg.reservas.reservasalones.controller;

import com.umg.reservas.reservasalones.dto.ReservaRequestDto;
import com.umg.reservas.reservasalones.dto.ReservaResponseDto;
import com.umg.reservas.reservasalones.dto.ResumenIndicadorDto;
import com.umg.reservas.reservasalones.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDto>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerPorIdDto(id));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<ReservaResponseDto>> buscarPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(reservaService.buscarPorFecha(fecha));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaResponseDto>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(reservaService.buscarPorEstado(estado));
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResumenIndicadorDto> obtenerResumen() {
        return ResponseEntity.ok(reservaService.obtenerResumen());
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDto> crearReserva(@Valid @RequestBody ReservaRequestDto dto) {
        return new ResponseEntity<>(reservaService.crearReserva(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDto> actualizarReserva(
            @PathVariable Long id, @Valid @RequestBody ReservaRequestDto dto) {
        return ResponseEntity.ok(reservaService.actualizarReserva(id, dto));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDto> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }
}