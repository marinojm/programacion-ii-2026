package com.umg.reservas.reservasalones.controller;

import com.umg.reservas.reservasalones.dto.ReservaRequestDto;
import com.umg.reservas.reservasalones.entity.Reserva;
import com.umg.reservas.reservasalones.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Reserva>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody ReservaRequestDto dto) {
        return new ResponseEntity<>(reservaService.crearReserva(dto), HttpStatus.CREATED);
    }
}
